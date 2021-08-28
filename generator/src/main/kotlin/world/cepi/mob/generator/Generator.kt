package world.cepi.mob.generator

import com.squareup.kotlinpoet.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializerOrNull
import net.minestom.server.coordinate.Pos
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.EntityMeta
import net.minestom.server.instance.block.Block
import net.minestom.server.item.ItemStack
import org.reflections.Reflections
import world.cepi.kstom.serializer.*
import world.cepi.mob.meta.MobMeta
import java.lang.reflect.Modifier
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.deleteIfExists
import kotlin.io.path.isDirectory

val superMobMeta = Reflections("net.minestom.server.entity.metadata")
    .getSubTypesOf(EntityMeta::class.java)
    .filter { Modifier.isPublic(it.modifiers) }

fun fixType(javaClass: Class<*>) = when (javaClass) {
    java.lang.Integer::class.java -> Int::class
    java.lang.String::class.java -> String::class
    else -> javaClass.kotlin
}

@OptIn(InternalSerializationApi::class)
fun <T : Any> generateMobMeta(clazz: Class<T>, simpleName: String): FileSpec? = FileSpec.builder("world.cepi.mob.meta", simpleName)
    .addType(TypeSpec.objectBuilder(simpleName)
        .addAnnotation(Serializable::class)
        .also { typeSpecBuilder ->
            clazz.declaredMethods
                .filter { it.parameterCount != 0 }
                .filter { it.name.contains("set") }
                .filter { Modifier.isPublic(it.modifiers) }
                .also { if (it.isEmpty()) return null }
                .map { method ->
                    TypeSpec.classBuilder(
                        method.name
                            .drop("set".length)
                            .replaceFirstChar { it.uppercase() }
                    )
                        .addAnnotation(Serializable::class)
                        .addModifiers(KModifier.DATA)
                        .addAnnotation(AnnotationSpec.builder(SerialName::class)
                            .addMember("%S", "${simpleName}_${method.name}").build())
                        .primaryConstructor(FunSpec.constructorBuilder()

                            .also { builder ->
                                method.parameters.forEach {
                                    builder.addParameter(it.name, fixType(it.type))
                                }
                            }
                            .build()
                        )
                        .also { builder ->
                            method.parameters.forEach {

                                builder.addProperty(PropertySpec.builder(it.name, fixType(it.type))
                                    .initializer(it.name)
                                    .also property@ { propertySpecBuilder ->

                                        if (fixType(it.type).serializerOrNull() != null) return@property

                                        propertySpecBuilder.addAnnotation(AnnotationSpec.builder(Serializable::class).addMember("%T::class", run {
                                            when (fixType(it.type)) {
                                                UUID::class -> UUIDSerializer::class
                                                ItemStack::class -> ItemStackSerializer::class
                                                Pos::class -> PositionSerializer::class
                                                Block::class -> BlockSerializer::class
                                                Vec::class -> VectorSerializer::class
                                                else -> return@map null
                                            }
                                        }).build())
                                    }
                                    .build()
                                )
                            }
                        }
                        .superclass(MobMeta::class)
                        .addFunction(FunSpec.builder("apply")
                            .addModifiers(KModifier.OVERRIDE)
                            .addParameter("entity", Entity::class)
                            .addStatement("(entity.entityMeta as? %T ?: return).${method.name}(${method.parameters.joinToString { it.name }})", clazz)
                            .build()
                        )
                        .build()

            }.filterNotNull().also { if (it.isEmpty()) return null }.forEach { typeSpecBuilder.addType(it) }
        }
        .build()
    )
    .build()

fun main() {

    val rootPath = Path.of("").toAbsolutePath().parent.resolve("src/autogenerated/kotlin")

    Files.walk(rootPath).forEach {
        if (it == rootPath) return@forEach

        if (it.isDirectory() && it.toList().isNotEmpty()) return@forEach

        it.deleteIfExists()
    }

    superMobMeta.forEach {
        val generatedFileSpec = generateMobMeta(it, "Meta" + it.simpleName.dropLast("Meta".length))

        generatedFileSpec?.writeTo(rootPath)
    }

}