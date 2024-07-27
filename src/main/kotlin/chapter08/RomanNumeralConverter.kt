package me.bossm0n5t3r.chapter08

object RomanNumeralConverter {
    private val table =
        mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000,
        )

    fun convert(numberInRoman: String): Int {
        var finalNumber = 0
        var lastNeighbor = 0
        for (i in numberInRoman.length - 1 downTo 0) {
            // get integer referent to current symbol
            val current = table[numberInRoman[i]] ?: error("Not found numberInRoman: ${numberInRoman[i]}")

            // if right is lower, multiply it
            // by -1 to turn it negative
            val multiplier =
                if (current < lastNeighbor) {
                    -1
                } else {
                    1
                }

            finalNumber += current * multiplier

            // update neighbor at right
            lastNeighbor = current
        }
        return finalNumber
    }
}
