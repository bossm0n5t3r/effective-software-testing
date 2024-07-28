package me.bossm0n5t3r.chapter08

data class Frame(
    val firstPins: Int,
    val secondPins: Int? = null,
    val thirdPins: Int? = null,
) {
    val totalScore: Int by lazy {
        this.firstPins + (this.secondPins ?: 0) + (thirdPins ?: 0)
    }

    val isStrike: Boolean by lazy {
        firstPins == 10 && secondPins == null && thirdPins == null
    }

    val isSpare: Boolean by lazy {
        firstPins < 10 && secondPins != null && firstPins + secondPins == 10
    }
}
