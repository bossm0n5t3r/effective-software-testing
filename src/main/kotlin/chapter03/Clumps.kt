package me.bossm0n5t3r.chapter03

object Clumps {
    /**
     * Counts the number of "clumps" that are in the array. A clump is a sequence of
     * the same element with a length of at least 2.
     *
     * @param nums
     * the array to count the clumps of. The array must be non-null and
     * len > 0; the program returns 0 in case any pre-condition is
     * violated.
     * @return the number of clumps in the array.
     */
    fun countClumps(nums: IntArray?): Int {
        if (nums == null || nums.isEmpty()) {
            return 0
        }
        var count = 0
        var prev = nums[0]
        var inClump = false
        for (i in 1 until nums.size) {
            if (nums[i] == prev && !inClump) {
                inClump = true
                count += 1
            }
            if (nums[i] != prev) {
                prev = nums[i]
                inClump = false
            }
        }
        return count
    }
}
