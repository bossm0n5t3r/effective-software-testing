package me.bossm0n5t3r.chapter05

object ArrayUtils {
    fun indexOf(
        array: IntArray?,
        valueToFind: Int,
        startIndex: Int,
    ): Int {
        if (array == null) {
            return -1
        }
        val modifiedStartIndex =
            startIndex
                .takeIf { startIndex >= 0 }
                ?: 0
        for (i in modifiedStartIndex until array.size) {
            if (valueToFind == array[i]) {
                return i
            }
        }
        return -1
    }
}
