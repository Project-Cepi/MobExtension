package world.cepi.mobextension.meta

import world.cepi.mobextension.StaticObjectCollection
import kotlin.reflect.KClass

object MetaRegistry: StaticObjectCollection<KClass<out MobMeta>> {
    override val objects = listOf(
            HealthMeta::class,
            NameMeta::class,
            GlowingMeta::class,
            FireMeta::class,
            SilentMeta::class,
            GravityMeta::class,
            InvisibleMeta::class
    )
}