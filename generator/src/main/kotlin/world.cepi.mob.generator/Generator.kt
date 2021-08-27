package world.cepi.mob.generator

import com.squareup.kotlinpoet.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.EntityMeta
import net.minestom.server.entity.metadata.MobMeta
import org.reflections.Reflections
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.deleteIfExists
import kotlin.io.path.isDirectory

val superMobMeta = Reflections("net.minestom.server.entity.metadata").getSubTypesOf(EntityMeta::class.java)

fun <T : Any> generateMobMeta(clazz: Class<T>): FileSpec? = FileSpec.builder("world.cepi.mob.meta", clazz.simpleName)
    .addType(TypeSpec.objectBuilder(clazz.simpleName)
        .also { typeSpecBuilder ->
            clazz.declaredMethods
                .filter { it.parameterCount != 0 }
                .also { if (it.isEmpty()) return null }
                .forEach { method ->
                    typeSpecBuilder.addType(
                        TypeSpec.classBuilder(
                            method.name
                                .drop("set".length)
                                .replaceFirstChar { it.uppercase() }
                        )
                            .addAnnotation(Serializable::class)
                            .addModifiers(KModifier.DATA)
                            .addAnnotation(AnnotationSpec.builder(SerialName::class)
                                .addMember("%S", "${clazz.simpleName}_${method.name}").build())
                            .primaryConstructor(FunSpec.constructorBuilder()
                                .also { builder ->
                                    method.parameters.forEach {
                                        builder.addParameter(it.name, it.type)
                                    }
                                }
                                .build()
                            )
                            .superclass(MobMeta::class)
                            .addFunction(FunSpec.builder("apply")
                                .addModifiers(KModifier.OVERRIDE)
                                .addParameter("entity", Entity::class)
                                .addStatement("(entity as? %T ?: return).${method.name}(${method.parameters.joinToString { it.name }})", clazz)
                                .build()
                            )
                            .build()
                    )
            }
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
        val generatedFileSpec = generateMobMeta(it)

        generatedFileSpec?.writeTo(rootPath)
    }

}