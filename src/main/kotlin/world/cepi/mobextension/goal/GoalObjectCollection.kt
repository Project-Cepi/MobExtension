package world.cepi.mobextension.goal

import world.cepi.mobextension.StaticObjectCollection
import kotlin.reflect.KClass

object GoalObjectCollection: StaticObjectCollection<KClass<out SerializableGoal>> {
    override val objects: List<KClass<out SerializableGoal>> = listOf(
        SerializableGoals.DoNothingGoal::class,
        SerializableGoals.RandomStrollGoal::class,
        SerializableGoals.RandomLookAroundGoal::class,
        SerializableGoals.FollowTargetGoal::class,
        SerializableGoals.MeleeAttackGoal::class
    )
}