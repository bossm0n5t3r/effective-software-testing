package chapter05

import me.bossm0n5t3r.chapter05.PassingGrade
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import net.jqwik.api.constraints.FloatRange
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PassingGradePBTest {
    private val sut = PassingGrade()

    @Property
    fun fail(
        @ForAll
        @FloatRange(min = 1.0f, max = 5.0f, maxIncluded = false)
        grade: Float,
    ) {
        assertFalse { sut.passed(grade) }
    }

    @Property
    fun passed(
        @ForAll
        @FloatRange(min = 5.0f, max = 10.0f, maxIncluded = true)
        grade: Float,
    ) {
        assertTrue { sut.passed(grade) }
    }

    @Property
    fun invalid(
        @ForAll("invalidGrades")
        grade: Float,
    ) {
        assertThrows<IllegalArgumentException> { sut.passed(grade) }
    }

    @Provide
    fun invalidGrades(): Arbitrary<Float> =
        Arbitraries.oneOf(
            Arbitraries.floats().lessThan(1f),
            Arbitraries.floats().greaterThan(10f),
        )
}
