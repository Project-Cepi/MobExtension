package world.cepi.mobextension.mob.conditional

interface ConditionalHolder {

    val conditions: MutableList<Conditional>

    fun addConditional(conditional: Conditional) = conditions.add(conditional)
    fun addConditional(conditionalBuilder: ConditionalBuilder) = conditions.add(conditionalBuilder.build())

}