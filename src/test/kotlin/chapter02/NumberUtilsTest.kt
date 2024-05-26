package chapter02

import me.bossm0n5t3r.chapter02.NumberUtils
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NumberUtilsTest {
    @ParameterizedTest
    @MethodSource("testCases")
    fun shouldReturnCorrectResult(
        left: List<Int>?,
        right: List<Int>?,
        expected: List<Int>?,
    ) {
        assertThat(NumberUtils.add(left, right))
            .isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("digitsOutOfRange")
    fun shouldThrowExceptionWhenDigitsAreOutOfRange(
        left: List<Int>?,
        right: List<Int>?,
    ) {
        assertThatThrownBy { NumberUtils.add(left, right) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    companion object {
        // returns a mutable list of integers
        private fun numbers(vararg nums: Int): List<Int> = nums.toList()

        @JvmStatic
        private fun testCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(null, numbers(7, 2), null),
                Arguments.of(numbers(), numbers(7, 2), numbers(7, 2)),
                Arguments.of(numbers(9, 8), null, null),
                Arguments.of(numbers(9, 8), numbers(), numbers(9, 8)),
                Arguments.of(numbers(1), numbers(2), numbers(3)),
                Arguments.of(numbers(9), numbers(2), numbers(1, 1)),
                Arguments.of(numbers(2, 2), numbers(3, 3), numbers(5, 5)),
                Arguments.of(numbers(2, 9), numbers(2, 3), numbers(5, 2)),
                Arguments.of(numbers(2, 9, 3), numbers(1, 8, 3), numbers(4, 7, 6)),
                Arguments.of(numbers(1, 7, 9), numbers(2, 6, 8), numbers(4, 4, 7)),
                Arguments.of(numbers(1, 9, 1, 7, 1), numbers(1, 8, 1, 6, 1), numbers(3, 7, 3, 3, 2)),
                Arguments.of(numbers(9, 9, 8), numbers(1, 7, 2), numbers(1, 1, 7, 0)),
                Arguments.of(numbers(2, 2), numbers(3), numbers(2, 5)),
                Arguments.of(numbers(3), numbers(2, 2), numbers(2, 5)),
                Arguments.of(numbers(2, 2), numbers(9), numbers(3, 1)),
                Arguments.of(numbers(9), numbers(2, 2), numbers(3, 1)),
                Arguments.of(numbers(1, 7, 3), numbers(9, 2), numbers(2, 6, 5)),
                Arguments.of(numbers(9, 2), numbers(1, 7, 3), numbers(2, 6, 5)),
                Arguments.of(numbers(3, 1, 7, 9), numbers(2, 6, 8), numbers(3, 4, 4, 7)),
                Arguments.of(numbers(2, 6, 8), numbers(3, 1, 7, 9), numbers(3, 4, 4, 7)),
                Arguments.of(numbers(1, 9, 1, 7, 1), numbers(2, 1, 8, 1, 6, 1), numbers(2, 3, 7, 3, 3, 2)),
                Arguments.of(numbers(2, 1, 8, 1, 6, 1), numbers(1, 9, 1, 7, 1), numbers(2, 3, 7, 3, 3, 2)),
                Arguments.of(numbers(9, 9, 8), numbers(9, 1, 7, 2), numbers(1, 0, 1, 7, 0)),
                Arguments.of(numbers(9, 1, 7, 2), numbers(9, 9, 8), numbers(1, 0, 1, 7, 0)),
                Arguments.of(numbers(0, 0, 0, 1, 2), numbers(0, 2, 3), numbers(3, 5)),
                Arguments.of(numbers(0, 0, 0, 1, 2), numbers(0, 2, 9), numbers(4, 1)),
                Arguments.of(numbers(9, 9), numbers(1), numbers(1, 0, 0)),
            )
        }

        @JvmStatic
        private fun digitsOutOfRange(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(numbers(1, -1, 1), numbers(1)),
                Arguments.of(numbers(1), numbers(1, -1, 1)),
                Arguments.of(numbers(1, 11, 1), numbers(1)),
                Arguments.of(numbers(1), numbers(1, 11, 1)),
            )
        }
    }
}
