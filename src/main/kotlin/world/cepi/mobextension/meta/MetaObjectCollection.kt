package world.cepi.mobextension.meta

import world.cepi.mobextension.StaticObjectCollection
import kotlin.reflect.KClass

object MetaObjectCollection: StaticObjectCollection<KClass<out MobMeta>> {
    override val objects = MobMeta::class.sealedSubclasses
}