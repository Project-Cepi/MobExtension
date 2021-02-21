package world.cepi.mobextension.targets

import world.cepi.mobextension.StaticObjectCollection
import kotlin.reflect.KClass

object TargetObjectCollection : StaticObjectCollection<KClass<out SerializableTarget>> {
    override val objects = listOf(
        SerializableTargets.SimplifiedClosestEntityTarget::class,
        SerializableTargets.SimplifiedClosestLivingEntityTarget::class,
        SerializableTargets.SimplifiedClosestPlayerTarget::class,
        SerializableTargets.SimplifiedLastEntityDamagerTarget::class
    )
}