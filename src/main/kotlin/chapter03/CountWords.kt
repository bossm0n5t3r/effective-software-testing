package me.bossm0n5t3r.chapter03

class CountWords {
    fun count(str: String): Int {
        var words = 0
        var last = ' '
        for (c in str) {
            if (c.isLetter().not() && (last == 's' || last == 'r')) {
                words++
            }
            last = c
        }
        if (last == 'r' || last == 's') words++
        return words
    }
}
