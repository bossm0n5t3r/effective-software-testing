package me.bossm0n5t3r.chapter05

object Triangle {
    fun isTriangle(
        a: Int,
        b: Int,
        c: Int,
    ): Boolean {
        val hasABadSide = (a >= (b + c) || c >= b + a) || b >= (a + c)
        return !hasABadSide
    }
}
