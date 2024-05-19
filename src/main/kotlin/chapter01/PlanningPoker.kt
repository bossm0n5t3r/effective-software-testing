package me.bossm0n5t3r.chapter01

class PlanningPoker {
    fun identifyExtremes(estimates: List<Estimate>?): List<String?> {
        requireNotNull(estimates) { "Estimates can't be null" }
        require(estimates.size > 1) { "There has to be more than 1 estimate in the list" }

        var lowestEstimate: Estimate? = null
        var highestEstimate: Estimate? = null

        for (estimate in estimates) {
            if (highestEstimate == null ||
                estimate.estimate > highestEstimate.estimate
            ) {
                highestEstimate = estimate
            }
            if (lowestEstimate == null ||
                estimate.estimate < lowestEstimate.estimate
            ) {
                lowestEstimate = estimate
            }
        }

        if (lowestEstimate == highestEstimate) return emptyList()

        return listOf(
            lowestEstimate?.developer,
            highestEstimate?.developer,
        )
    }
}
