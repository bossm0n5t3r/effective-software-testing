package me.bossm0n5t3r.chapter09.large

class FinalPriceCalculatorFactory {
    fun build(): FinalPriceCalculator {
        val priceRules =
            listOf(
                PriceOfItems(),
                ExtraChargeForElectronics(),
                DeliveryPrice(),
            )

        return FinalPriceCalculator(priceRules)
    }
}
