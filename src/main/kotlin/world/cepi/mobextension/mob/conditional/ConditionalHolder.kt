package world.cepi.mobextension.mob.conditional

open class ConditionalHolder {

    val conditions: MutableList<Conditional> = mutableListOf()

    fun addConditonal(conditional: Conditional) {
        conditions.add(conditional)

    }

}