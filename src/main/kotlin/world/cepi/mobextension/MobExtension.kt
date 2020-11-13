package world.cepi.mobextension

import com.beust.klaxon.json
import com.google.gson.Gson
import com.google.gson.JsonParser
import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension;
import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import world.cepi.mobextension.MobExtension.Companion.words
import world.cepi.mobextension.commands.MobCommand
import java.io.File

class MobExtension : Extension() {

    private fun getWords(): Array<String> {
        val request = Request(Method.GET, "https://raw.githubusercontent.com/dwyl/english-words/master/words_dictionary.json")
        val client: HttpHandler = JavaHttpClient()
        val jsonObject = JsonParser.parseString(client(request).bodyString()).asJsonObject
        val listToReturn = mutableListOf<String>()
        jsonObject.keySet().forEach { key -> listToReturn.add(key) }
        return listToReturn.toTypedArray()
    }

    override fun initialize() {
        words = getWords()
        MinecraftServer.getCommandManager().register(MobCommand())
        logger.info("[MobExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[MobExtension] has been disabled!")
    }

    companion object {
     val dataDir: File
        get() {
            val dir = File("./extensions/MobExtension")
            if (!dir.exists()) dir.mkdirs()
            return dir
        }

        var words: Array<String>? = null
    }
}

fun genRandomID(): String {
    val idList = mutableListOf<String>()
    for (string in 0 until 3) {
        idList.add(words!!.random())
    }
    return idList.joinToString("-")
}