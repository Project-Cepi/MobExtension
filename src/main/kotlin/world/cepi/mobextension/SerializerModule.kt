package world.cepi.mobextension

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import world.cepi.mobextension.goal.SerializableGoal
import world.cepi.mobextension.goal.SerializableGoals
import world.cepi.mobextension.meta.*
import world.cepi.mobextension.targets.SerializableTarget
import world.cepi.mobextension.targets.SerializableTargets

/**
 * List of polymorphism modules for kotlinx.serializable
 */

// TODO use the static object collection.

val module = SerializersModule {

    polymorphic(MobMeta::class) {
        subclass(HealthMeta::class)
        subclass(NameMeta::class)
        subclass(GlowingMeta::class)
        subclass(FireMeta::class)
        subclass(SilentMeta::class)
        subclass(GravityMeta::class)
        subclass(InvisibleMeta::class)
        subclass(ExperienceMeta::class)
    }

    polymorphic(SerializableGoal::class) {

        subclass(SerializableGoals.FollowTargetGoal::class)
        subclass(SerializableGoals.RandomStrollGoal::class)
        subclass(SerializableGoals.RandomLookAroundGoal::class)
        subclass(SerializableGoals.MeleeAttackGoal::class)
        subclass(SerializableGoals.DoNothingGoal::class)
        subclass(SerializableGoals.RangedAttackGoal::class)
    }

    polymorphic(SerializableTarget::class) {

        subclass(SerializableTargets.SimplifiedClosestEntityTarget::class)
        subclass(SerializableTargets.SimplifiedLastEntityDamagerTarget::class)
        subclass(SerializableTargets.SimplifiedClosestLivingEntityTarget::class)
        subclass(SerializableTargets.SimplifiedClosestPlayerTarget::class)

    }
}