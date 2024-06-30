package chapter05

import me.bossm0n5t3r.chapter05.ArrayUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ArrayUtilsTest {
    @ParameterizedTest
    @MethodSource("testCases")
    fun testIndexOf(
        array: IntArray?,
        valueToFind: Int,
        startIndex: Int,
        expectedResult: Int,
    ) {
        val result: Int = ArrayUtils.indexOf(array, valueToFind, startIndex)
        assertThat(result).isEqualTo(expectedResult)
    }
    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> {
            val array = intArrayOf(1, 2, 3, 4, 5, 4, 6, 7)

            return Stream.of(
                of(null, 1, 1, -1),
                of(intArrayOf(1), 1, 0, 0),
                of(intArrayOf(1), 2, 0, -1),
                of(array, 1, 10, -1),
                of(array, 2, -1, 1),
                of(array, 4, 6, -1),
                of(array, 4, 1, 3),
                of(array, 4, 3, 3),
                of(array, 4, 1, 3),
                of(array, 4, 4, 5),
                of(array, 8, 0, -1),
            )
        }
    }
}
