package me.bossm0n5t3r.chapter02

object StringUtils {
    fun substringsBetween(
        str: String?,
        open: String?,
        close: String?,
    ): List<String>? {
        if (str == null || open.isNullOrEmpty() || close.isNullOrEmpty()) return null

        val strLength = str.length
        if (strLength == 0) return emptyList()

        val openLength = open.length
        val closeLength = close.length
        val result = mutableListOf<String>()
        var position = 0

        while (position < strLength - closeLength) {
            var start = str.indexOf(open, position)

            if (start < 0) break

            start += openLength
            val end = str.indexOf(close, start)
            if (end < 0) break

            result.add(str.substring(start, end))
            position = end + closeLength
        }

        return result.takeIf { it.isNotEmpty() }
    }
}
