package chapter03

import me.bossm0n5t3r.chapter03.CountWords
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CountWordsTest {
    private val sut = CountWords()

    @Test
    fun whenIsLetterFalseAndLastIsS() {
        assertThat(sut.count("dogs cats")).isEqualTo(2)
    }

    @Test
    fun whenIsLetterFalseAndLastIsR() {
        assertThat(sut.count("car bar")).isEqualTo(2)
    }

    @Test
    fun whenIsLetterFalseAndLastIsNotSAndR() {
        assertThat(sut.count("dog cat")).isEqualTo(0)
    }

    @Test
    fun whenIsLetterTrueAndLastIsS() {
        assertThat(sut.count("words")).isEqualTo(1)
    }
}
