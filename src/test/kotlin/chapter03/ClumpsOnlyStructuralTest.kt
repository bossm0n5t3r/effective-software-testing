package chapter03

import me.bossm0n5t3r.chapter03.Clumps
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ClumpsOnlyStructuralTest {
    @ParameterizedTest
    @MethodSource("generator")
    fun testClumps(
        nums: IntArray?,
        expectedNoOfClumps: Int,
    ) {
        assertThat(Clumps.countClumps(nums))
            .isEqualTo(expectedNoOfClumps)
    }

    companion object {
        @JvmStatic
        private fun generator(): Stream<Arguments> {
            return Stream.of(
                // empty
                of(intArrayOf(), 0),
                // null
                of(null, 0),
                // one clump
                of(intArrayOf(1, 2, 2, 2, 1), 1),
                // one element
                of(intArrayOf(1), 0),
                // an example of a missing test case!! Structural testing is not enough!
                of(intArrayOf(2, 2), 1),
            )
        }
    }
}
