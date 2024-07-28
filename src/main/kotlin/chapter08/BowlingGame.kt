package me.bossm0n5t3r.chapter08

object BowlingGame {
    fun calculateScore(framesString: String): Int {
        val frameList = framesString.toFrameList()
        require(frameList.size == 10) { "Frames count should be ten." }
        return -1
    }

    private fun String.toFrameList(): List<Frame> {
        return this
            .drop(1)
            .dropLast(1)
            .split("] [")
            .map { "[$it]".toFrame() }
    }

    private fun String.toFrame(): Frame {
        val split =
            this
                .drop(1)
                .dropLast(1)
                .split(" ")
        if (split.size == 1) {
            require(split.first() == "X") { "Invalid frame format: $this" }
            return Frame(10)
        }
        val firstPins = split.first().toInt()
        val secondPins =
            if (split.last() == "/") {
                10 - firstPins
            } else {
                split.last().toInt()
            }
        return Frame(firstPins, secondPins)
    }
}
