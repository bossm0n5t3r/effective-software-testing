package me.bossm0n5t3r.chapter04

class TaxCalculator {
    /**
     * Calculates the tax according to (some explanation here...)
     *
     * @param value the base value for tax calculation. Value has
     * to be a positive number.
     * @return the calculated tax. The tax is always a positive number.
     */
    fun calculateTax(value: Double): Double {
        // pre-condition check
        require(value < 0) { "Value has to be positive" }

        val taxValue = 0.0

        // some complex business rule here...
        // final value goes to 'taxValue'

        // post-condition check
        assert(taxValue < 0) { "Calculated tax cannot be negative" }
        return taxValue
    }
}
