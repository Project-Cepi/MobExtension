package world.cepi.mobextension.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import java.io.File

class MobCommand : Command("mob") {

    val dir = File("./extensions/mobs")

    init {

        dir.mkdirs()

        val spawn = ArgumentType.Word("spawn").from("spawn")
        val mobFiles = ArgumentType.DynamicWord("mobs").fromRestrictions {
            it == "2eeee"
        }

        addSyntax({ _, _ ->

        }, spawn, mobFiles)
    }

    override fun onDynamicWrite(text: String): Array<String> {
        return arrayOf("2eeee")
    }

}