package world.cepi.mobextension.mob.serialize

import com.worldturner.medeia.api.SchemaSource
import com.worldturner.medeia.api.UrlSchemaSource
import java.io.File
import java.io.FilenameFilter

import com.worldturner.medeia.api.ValidationFailedException
import com.worldturner.medeia.api.gson.MedeiaGsonApi
import com.worldturner.medeia.schema.validation.SchemaValidator
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import world.cepi.mobextension.MobExtension
import java.net.URL

val medeia = MedeiaGsonApi()

fun serialize() {
    val mobFolder = File("./mobs")
    if (!mobFolder.exists()) mobFolder.mkdirs()

    val filter = FilenameFilter { _, name -> name.endsWith(".json") }
    val schema = getSchema()
    mobFolder.list(filter)?.forEach {
        val file = File("./mobs/$it")
        if (!file.exists()) return@forEach
        try {
            medeia.parseAll(medeia.createJsonReader(schema, file.reader()))
        } catch (e: ValidationFailedException) { MobExtension.logger.error("[MobExtension] $it cannot be loaded due to invalid JSON!"); return }
    }
}

private fun getSchema(): SchemaValidator = medeia.loadSchema(UrlSchemaSource(URL("https://api.cepi.world/mobschema")))
