package chapter03

import me.bossm0n5t3r.chapter03.LeftPadUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LeftPadUtilsTest {
    @ParameterizedTest
    @MethodSource("generator")
    fun test(
        originalStr: String?,
        size: Int,
        padString: String?,
        expectedStr: String?,
    ) {
        assertThat(LeftPadUtils.leftPad(originalStr, size, padString))
            .isEqualTo(expectedStr)
    }

    @Test
    fun sameInstance() {
        val str = "sometext"
        assertThat(LeftPadUtils.leftPad(str, 5, "-"))
            .isSameAs(str)
    }

    companion object {
        @JvmStatic
        private fun generator(): Stream<Arguments> {
            return Stream.of(
                // T1
                of(null, 10, "-", null),
                // T2
                of("", 5, "-", "-----"),
                // T3
                of("abc", -1, "-", "abc"),
                // T4
                of("abc", 5, null, "  abc"),
                // T5
                of("abc", 5, "", "  abc"),
                // T6
                of("abc", 5, "-", "--abc"),
                // T7
                of("abc", 3, "-", "abc"),
                // T8
                of("abc", 0, "-", "abc"),
                // T9
                of("abc", 2, "-", "abc"),
                // T10
                of("abc", 5, "--", "--abc"),
                // T11
                of("abc", 5, "---", "--abc"),
                // T12
                of("abc", 5, "-", "--abc"),
            )
        }
    }
}
