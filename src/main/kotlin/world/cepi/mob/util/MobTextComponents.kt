package world.cepi.mob.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.utils.time.UpdateOption
import org.jetbrains.annotations.Contract
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

internal object MobTextComponents {

    // Special case for any non-stringable classes.
    private fun classToStringProperties(obj: Any): String {
        return when (obj::class) {
            UpdateOption::class -> {
                obj as UpdateOption

                return "${obj.value} ${obj.timeUnit}"
            }
            else -> obj.toString()
        }
    }

    /**
     * Turns a list of mob properties and makes them human readable.
     *
     * @param pluralName The plural version of the name, ex "Meta" / "Goals"
     * @param unknownProperty The unknown version of the name, ex "Unknown Meta" / "Unknown Goal"
     * @param dropLast The string to drop off the class name, ex "Goal" / "Meta"
     * @param properties The list of properties (objects, not classes)
     *
     * @return A component following this format:
     *                 Goals:
     *                 
     */
    @Contract(pure = true)
    internal fun mobPropertiesToComponent(
        pluralName: String,
        unknownProperty: String,
        dropLast: String,
        properties: Collection<Any>
    ): Component {
        return Component.text("$pluralName: ", NamedTextColor.GRAY)
            .append(Component.newline())
            .let { component ->

                // If the [properties] value is empty just don't do anything else.
                if (properties.isEmpty()) return@let component

                // Map all values to (key: value)
                return@let component.append(createPropertyMap(unknownProperty, dropLast, properties))
            }
    }

    @Contract(pure = true)
    internal fun createPropertyMap(
        unknownProperty: String,
        dropLast: String,
        properties: Collection<Any>
    ): Component {
        return properties.map { target ->
            // Drop the "drop" keyword's length from the target class's name (if the name doesnt exist use unknownProperty)
            Component.text((target::class.simpleName?.dropLast(dropLast.length)) ?: unknownProperty, NamedTextColor.WHITE)
                // Upcoming (key: value...) component
                .append(Component.text(" (${
                    // Combine all the member properties of the target to (key: value...)
                    target::class.memberProperties.joinToString { value ->

                        // super hacky hack in order to trick java into thinking this hacky property is fine
                        @Suppress("UNCHECKED_CAST")
                        value as KProperty1<Any, *>

                        // Then return key: value)
                        "${value.name}: ${classToStringProperties(value.get(target)!!)}"
                    }
                })", NamedTextColor.GRAY))
                .append(Component.newline())
        }.reduce { acc, textComponent -> acc.append(textComponent) }
    }
}