package world.cepi.mobextension.api.goals

import net.minestom.server.entity.ai.GoalSelector

data class Goal(val priority: Int, val type: GoalSelector) {
}