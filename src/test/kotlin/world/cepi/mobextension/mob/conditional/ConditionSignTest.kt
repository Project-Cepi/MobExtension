package world.cepi.mobextension.mob.conditional

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// TODO more tests to test all condition signs.
class ConditionSignTest {
    @Test
    fun `2 numbers should properly be equal with the equal comparison`() {
        assertTrue(ConditionSign.EQUAL.comparison.invoke(5f, 5f))
        assertFalse(ConditionSign.EQUAL.comparison.invoke(15f, 10f))
    }
}