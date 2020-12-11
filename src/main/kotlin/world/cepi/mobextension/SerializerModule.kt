package world.cepi.mobextension

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import world.cepi.mobextension.goal.SerializableGoal
import world.cepi.mobextension.goal.SerializableGoals
import world.cepi.mobextension.meta.GlowingMeta
import world.cepi.mobextension.meta.HealthMeta
import world.cepi.mobextension.meta.MobMeta
import world.cepi.mobextension.meta.NameMeta
import world.cepi.mobextension.targets.SerializableTarget
import world.cepi.mobextension.targets.SerializableTargets

val module = SerializersModule {
    polymorphic(MobMeta::class) {
        subclass(HealthMeta::class)
        subclass(NameMeta::class)
        subclass(GlowingMeta::class)
    }

    polymorphic(SerializableGoal::class) {

        // TODO Get all nested classes from SerializableGoals

        subclass(SerializableGoals.FollowTargetGoal::class)
        subclass(SerializableGoals.RandomStrollGoal::class)
        subclass(SerializableGoals.RandomLookAroundGoal::class)
        subclass(SerializableGoals.MeleeAttackGoal::class)
        subclass(SerializableGoals.DoNothingGoal::class)
    }

    polymorphic(SerializableTarget::class) {

        subclass(SerializableTargets.ClosestEntityTarget::class)
        subclass(SerializableTargets.LastEntityDamagerTarget::class)

    }
}