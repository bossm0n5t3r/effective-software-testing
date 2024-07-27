package chapter08

import me.bossm0n5t3r.chapter08.RomanNumeralConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RomanNumeralConverterTest {
    @ParameterizedTest
    @CsvSource("I,1", "V,5", "X,10", "L,50", "C, 100", "D, 500", "M, 1000")
    fun shouldUnderstandOneCharNumbers(
        romanNumeral: String,
        expectedNumberAfterConversion: Int,
    ) {
        assertThat(RomanNumeralConverter.convert(romanNumeral)).isEqualTo(expectedNumberAfterConversion)
    }

    @ParameterizedTest
    @CsvSource("II,2", "III,3", "VI, 6", "XVIII, 18", "XXIII, 23", "DCCLXVI, 766")
    fun shouldUnderstandMultipleCharNumbers(
        romanNumeral: String,
        expectedNumberAfterConversion: Int,
    ) {
        assertThat(RomanNumeralConverter.convert(romanNumeral)).isEqualTo(expectedNumberAfterConversion)
    }

    @ParameterizedTest
    @CsvSource("IV,4", "XIV,14", "XL, 40", "XLI,41", "CCXCIV, 294")
    fun shouldUnderstandSubtractiveNotation(
        romanNumeral: String,
        expectedNumberAfterConversion: Int,
    ) {
        assertThat(RomanNumeralConverter.convert(romanNumeral)).isEqualTo(expectedNumberAfterConversion)
    }
}
