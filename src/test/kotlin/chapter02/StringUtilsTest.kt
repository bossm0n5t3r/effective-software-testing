package chapter02

import me.bossm0n5t3r.chapter02.StringUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringUtilsTest {
    /**
     * https://github.com/apache/commons-lang/blob/master/src/test/java/org/apache/commons/lang3/StringUtilsSubstringTest.java#L324-L383
     */
    @Test
    fun shouldNullWhenStrIsNull() {
        assertThat(StringUtils.substringsBetween(null, null, null))
            .isEqualTo(null)
    }

    @Test
    fun shouldNullWhenOpenIsNullOrEmpty() {
        assertThat(StringUtils.substringsBetween("", null, null))
            .isEqualTo(null)

        assertThat(StringUtils.substringsBetween("", "", null))
            .isEqualTo(null)
    }

    @Test
    fun shouldNullWhenCloseIsNullOrEmpty() {
        assertThat(StringUtils.substringsBetween("", "open", null))
            .isEqualTo(null)

        assertThat(StringUtils.substringsBetween("", "open", ""))
            .isEqualTo(null)
    }

    @Test
    fun shouldEmptyListWhenStrIsEmpty() {
        assertThat(StringUtils.substringsBetween("", "open", "close"))
            .isEqualTo(arrayOf<String>())
    }

    @Test
    fun shouldNullWhenStrDoesNotContainsOpen() {
        assertThat(StringUtils.substringsBetween("str", "open", "str"))
            .isEqualTo(null)
    }

    @Test
    fun shouldNullWhenStrDoesNotContainsClose() {
        assertThat(StringUtils.substringsBetween("str", "str", "close"))
            .isEqualTo(null)
    }

    @Test
    fun shouldNullWhenStrContainsOpenAndCloseRespectively() {
        assertThat(StringUtils.substringsBetween("a", "a", "a"))
            .isEqualTo(null)
    }

    @Test
    fun shouldNonEmptyListWhenStrContainsOpenAndClose() {
        assertThat(StringUtils.substringsBetween("axbaybazb", "a", "b"))
            .isEqualTo(arrayOf("x", "y", "z"))
        assertThat(StringUtils.substringsBetween("axbaybazb", "axb", "azb"))
            .isEqualTo(arrayOf("ayb"))
    }

    @Test
    fun shouldContainsEmptyStringWhenOpenAndCloseAreTogether() {
        assertThat(StringUtils.substringsBetween("ababab", "a", "b"))
            .isEqualTo(arrayOf("", "", ""))
    }

    @Test
    fun whenStrContainsWhitespace() {
        assertThat(StringUtils.substringsBetween(" ", " ", " "))
            .isEqualTo(null)
        assertThat(StringUtils.substringsBetween("No pain, No gain", "No", "No"))
            .isEqualTo(arrayOf(" pain, "))
    }

    @Test
    fun whenStrIsKorean() {
        assertThat(StringUtils.substringsBetween("제발 한 번에 푹 자게 해주세요", "제발", "해주세요"))
            .isEqualTo(arrayOf(" 한 번에 푹 자게 "))
    }
}
