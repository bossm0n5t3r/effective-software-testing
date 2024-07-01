package chapter05

import me.bossm0n5t3r.chapter05.Triangle
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.Combinators
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import net.jqwik.api.constraints.IntRange
import org.assertj.core.api.Assertions.assertThat

class TriangleTest {
    @Property
    fun triangleBadTest(
        @ForAll @IntRange(max = 100) a: Int,
        @ForAll @IntRange(max = 100) b: Int,
        @ForAll @IntRange(max = 100) c: Int,
    ) {
        // ... test here ...
    }

    @Property
    fun triangleIsInvalidIfOneSideIsBiggerThanOthers(
        @ForAll("invalidTrianglesGenerator") abc: ABC,
    ) {
        assertThat(Triangle.isTriangle(abc.a, abc.b, abc.c)).isFalse()
    }

    @Provide
    fun invalidTrianglesGenerator(): Arbitrary<ABC> {
        val normalSide1: Arbitrary<Int> = Arbitraries.integers()
        val normalSide2: Arbitrary<Int> = Arbitraries.integers()
        val biggerSide: Arbitrary<Int> = Arbitraries.integers()
        val biggerA =
            Combinators.combine(normalSide1, normalSide2, biggerSide)
                .`as` { a: Int, b: Int, c: Int -> ABC(a, b, c) }
                .filter { k: ABC -> k.a >= k.b + k.c }
        val biggerB =
            Combinators.combine(normalSide1, normalSide2, biggerSide)
                .`as` { a: Int, b: Int, c: Int -> ABC(a, b, c) }
                .filter { k: ABC -> k.b >= k.a + k.c }
        val biggerC =
            Combinators.combine(normalSide1, normalSide2, biggerSide)
                .`as` { a: Int, b: Int, c: Int -> ABC(a, b, c) }
                .filter { k: ABC -> k.c >= k.a + k.b }
        return Arbitraries.oneOf(biggerA, biggerB, biggerC)
    }

    @Property
    fun triangleIsValidOtherwise(
        @ForAll("validTriangleGenerator") abc: ABC,
    ) {
        assertThat(Triangle.isTriangle(abc.a, abc.b, abc.c)).isTrue()
    }

    @Provide
    fun validTriangleGenerator(): Arbitrary<ABC> {
        val normalSide1: Arbitrary<Int> = Arbitraries.integers()
        val normalSide2: Arbitrary<Int> = Arbitraries.integers()
        val normalSide3: Arbitrary<Int> = Arbitraries.integers()
        return Combinators.combine(normalSide1, normalSide2, normalSide3)
            .`as` { a: Int, b: Int, c: Int -> ABC(a, b, c) }
            .filter { k: ABC -> (k.a < k.b + k.c) && (k.b < k.a + k.c) && (k.c < k.a + k.b) }
    }

    // use the ABC class below.
    data class ABC(val a: Int, val b: Int, val c: Int)
}
