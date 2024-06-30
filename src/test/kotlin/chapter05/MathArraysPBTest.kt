package chapter05

import me.bossm0n5t3r.chapter05.MathArrays
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange
import net.jqwik.api.constraints.Size
import org.assertj.core.api.Assertions.assertThat

class MathArraysPBTest {
    @Property
    fun unique(
        @ForAll
        @Size(value = 100)
        numbers: List<
            @IntRange(min = 1, max = 20)
            Int,
            >,
    ) {
        val intValues: IntArray = numbers.toIntArray()
        val result: IntArray = MathArrays.unique(intValues)

        assertThat(result)
            .contains(*intValues) // contains all the elements
            .doesNotHaveDuplicates() // no duplicates
            .isSortedAccordingTo(Comparator.reverseOrder()) // in descending order
    }
}
