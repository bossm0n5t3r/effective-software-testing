package me.bossm0n5t3r.chapter02

import java.util.LinkedList

object NumberUtils {
    fun add(
        left: List<Int>?,
        right: List<Int>?,
    ): List<Int>? {
        // if any is null, return null
        if (left == null || right == null) return null

        // reverse the numbers so that the least significant digit goes to the left.
        val reversedLeft = left.reversed()
        val reversedRight = right.reversed()

        val result = LinkedList<Int>()

        // while there's a digit, keep summing them
        // if there's carry, take the carry into consideration
        var carry = 0
        for (i in 0 until maxOf(reversedLeft.size, reversedRight.size)) {
            val leftDigit = if (reversedLeft.size > i) reversedLeft[i] else 0
            val rightDigit = if (reversedRight.size > i) reversedRight[i] else 0

            require(leftDigit in 0..9 && rightDigit in 0..9)

            val sum = leftDigit + rightDigit + carry

            result.addFirst(sum % 10)
            carry = sum / 10
        }

        // if there's some leftover carry, add it to the final number
        if (carry > 0) result.addFirst(carry)

        // remove leading zeroes from the result
        while (result.isNotEmpty() && result[0] == 0) result.removeAt(0)

        return result
    }
}
