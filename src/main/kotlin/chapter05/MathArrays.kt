package me.bossm0n5t3r.chapter05

import java.util.TreeSet

object MathArrays {
    /**
     * https://github.com/apache/commons-math/blob/master/commons-math-legacy-core/src/main/java/org/apache/commons/math4/legacy/core/MathArrays.java#L1107-L1135
     */
    fun unique(data: IntArray): IntArray {
        val values = TreeSet<Int>()
        for (i in data.indices) {
            values.add(data[i])
        }
        val count = values.size
        val out = IntArray(count)
        val iterator = values.descendingIterator()
        var i = 0
        while (iterator.hasNext()) {
            out[i++] = iterator.next()
        }
        return out
    }
}
