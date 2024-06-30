package chapter05

import me.bossm0n5t3r.chapter05.ArrayUtils
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange
import net.jqwik.api.constraints.Size
import org.assertj.core.api.Assertions.assertThat

class ArrayUtilsPBTest {
    @Property
    fun indexOfShouldFindFirstValue(
        /**
         * we generate a list with 100 numbers, ranging from -1000, 1000. Range chosen
         * randomly.
         */
        @ForAll
        @Size(value = 100)
        numbers: MutableList<
            @IntRange(min = -1000, max = 1000)
            Int,
            >,
        /**
         * we generate a random number that we'll insert in the list. This number is
         * outside the range of the list, so that we can easily find it.
         */
        @ForAll
        @IntRange(min = 1001, max = 2000) value: Int,
        /** We randomly pick a place to put the element in the list  */
        @ForAll
        @IntRange(max = 99) indexToAddElement: Int,
        /** We randomly pick a number to start the search in the array  */
        @ForAll
        @IntRange(max = 99) startIndex: Int,
    ) {
        // we add the number to the list in the randomly chosen position
        numbers.add(indexToAddElement, value)

        // we convert the list to an array, as expected by the method
        val array: IntArray = numbers.toIntArray()

        /**
         *
         * if we added the element after the start index, then, we expect the method to
         * return the position where we inserted the element. Else, we expect the method
         * to return -1.
         */
        val expectedIndex = if (indexToAddElement >= startIndex) indexToAddElement else -1
        assertThat(ArrayUtils.indexOf(array, value, startIndex)).isEqualTo(expectedIndex)
    }
}
