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
        return when (split.size) {
            1 -> {
                require(split.first() == "X") { "Invalid frame format: $this" }
                Frame(10)
            }
            2 -> {
                val firstPins = split.first().toInt()
                val secondPins =
                    if (split.last() == "/") {
                        10 - firstPins
                    } else {
                        split.last().toInt()
                    }
                Frame(firstPins, secondPins)
            }
            3 -> {
                val (first, second, third) = split
                val firstPins = if (first == "X") 10 else first.toInt()
                val secondPins =
                    when (second) {
                        "X" -> 10
                        "/" -> 10 - firstPins
                        else -> second.toInt()
                    }
                val thirdPins = if (third == "X") 10 else third.toInt()
                Frame(firstPins, secondPins, thirdPins)
            }

            else -> error("Invalid frame format: $this")
        }
    }
}
