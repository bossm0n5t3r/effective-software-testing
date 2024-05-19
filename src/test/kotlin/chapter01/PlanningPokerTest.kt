package chapter01

import me.bossm0n5t3r.chapter01.Estimate
import me.bossm0n5t3r.chapter01.PlanningPoker
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.Combinators
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PlanningPokerTest {
    private val sut = PlanningPoker()

    @Test
    fun rejectNullInput() {
        assertThatThrownBy { sut.identifyExtremes(null) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun rejectEmptyList() {
        assertThatThrownBy { sut.identifyExtremes(emptyList()) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun rejectSingleEstimate() {
        assertThatThrownBy { sut.identifyExtremes(listOf(Estimate("Eleanor", 1))) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun twoEstimates() {
        val estimates =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Frank", 5),
            )

        val devs = sut.identifyExtremes(estimates)

        assertThat(devs)
            .containsExactlyInAnyOrder("Mauricio", "Frank")
    }

    @Test
    fun manyEstimates() {
        val estimates =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Arie", 5),
                Estimate("Frank", 7),
            )

        val devs = sut.identifyExtremes(estimates)

        assertThat(devs)
            .containsExactlyInAnyOrder("Mauricio", "Arie")
    }

    @Property
    fun estimatesInAnyOrder(
        @net.jqwik.api.ForAll("estimates") estimates: MutableList<Estimate>,
    ) {
        estimates.add(Estimate("MrLowEstimate", 1))
        estimates.add(Estimate("MsHighEstimate", 100))
        estimates.shuffle()

        val dev = PlanningPoker().identifyExtremes(estimates)

        assertThat(dev)
            .containsExactlyInAnyOrder("MrLowEstimate", "MsHighEstimate")
    }

    @Provide
    fun estimates(): Arbitrary<List<Estimate>> {
        val names: Arbitrary<String> = Arbitraries.strings().withCharRange('a', 'z').ofLength(5)
        val values: Arbitrary<Int> = Arbitraries.integers().between(2, 99)

        val estimates: Arbitrary<Estimate> =
            Combinators.combine(names, values)
                .`as` { name, value -> Estimate(name, value) }

        return estimates.list().ofMinSize(1)
    }

    @Test
    fun developersWithSameEstimates() {
        val list: List<Estimate> =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Arie", 5),
                Estimate("Andy", 10),
                Estimate("Frank", 7),
                Estimate("Annibale", 5),
            )

        val devs = PlanningPoker().identifyExtremes(list)

        assertThat(devs)
            .containsExactlyInAnyOrder("Mauricio", "Arie")
    }

    @Test
    fun allDevelopersWithTheSameEstimate() {
        val list: List<Estimate> =
            listOf(
                Estimate("Mauricio", 10),
                Estimate("Arie", 10),
                Estimate("Andy", 10),
                Estimate("Frank", 10),
                Estimate("Annibale", 10),
            )

        val devs = PlanningPoker().identifyExtremes(list)

        assertThat(devs).isEmpty()
    }
}
