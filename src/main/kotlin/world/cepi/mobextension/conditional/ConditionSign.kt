package world.cepi.mobextension.conditional

enum class ConditionSign(val comparison: (Float, Float) -> Boolean) {

    GREATER_THAN_EQUAL({ left, right -> left >= right }),
    GREATER_THAN({ left, right -> left > right }),
    EQUAL({ left, right -> left == right }),
    LESS_THAN({ left, right -> left < right }),
    LESS_THAN_EQUAL({ left, right -> left <= right }),

}