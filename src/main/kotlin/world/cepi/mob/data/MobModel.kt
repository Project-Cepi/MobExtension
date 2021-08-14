package world.cepi.mob.data

import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.model.JsonModel

object MobModel : JsonModel<RegisteredMob>(
    RegisteredMob.serializer(),
    DataNamespace("item", "mob"),
    { it.mobKey }
)