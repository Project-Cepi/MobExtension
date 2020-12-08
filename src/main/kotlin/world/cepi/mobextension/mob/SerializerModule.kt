package world.cepi.mobextension.mob

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import world.cepi.mobextension.goal.serializable.SerializableGoal
import world.cepi.mobextension.goal.serializable.SerializableGoals
import world.cepi.mobextension.mob.meta.GlowingMeta
import world.cepi.mobextension.mob.meta.HealthMeta
import world.cepi.mobextension.mob.meta.MobMeta
import world.cepi.mobextension.mob.meta.NameMeta

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
}