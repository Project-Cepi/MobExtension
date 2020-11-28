package world.cepi.mobextension.mob.conditional

open class ConditionalHolder {

    val conditions: MutableList<Conditional> = mutableListOf()

    fun addConditonal(conditional: Conditional) = conditions.add(conditional)
    fun addConditional(conditionalBuilder: ConditionalBuilder) = conditions.add(conditionalBuilder.build())

}