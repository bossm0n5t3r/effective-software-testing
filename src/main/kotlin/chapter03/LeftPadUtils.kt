package me.bossm0n5t3r.chapter03

object LeftPadUtils {
    private const val SPACE = " "

    /**
     * https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/StringUtils.java#L5274-L5326
     */
    fun leftPad(
        str: String?,
        size: Int,
        padStr: String?,
    ): String? {
        if (str == null) return null
        val padString =
            padStr
                .takeIf { it.isNullOrEmpty().not() }
                ?: SPACE

        val padLength = padString.length
        val strLength = str.length
        val pads = size - strLength

        if (pads <= 0) return str

        return when {
            pads == padLength -> "$padString$str"
            pads < padLength -> "${padString.substring(0, pads)}$str"
            else -> {
                val padding = CharArray(pads)
                val padChars = padString.toCharArray()

                for (i in 0 until pads) {
                    padding[i] = padChars[i % padLength]
                }

                "${String(padding)}$str"
            }
        }
    }
}
