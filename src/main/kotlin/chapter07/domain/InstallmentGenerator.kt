package me.bossm0n5t3r.chapter07.domain

import java.time.LocalDate

class InstallmentGenerator(
    private val repository: InstallmentRepository,
) {
    fun generateInstallments(
        cart: ShoppingCart,
        numberOfInstallments: Int,
    ) {
        // create a variable that will store the last installment date
        var nextInstallmentDueDate: LocalDate = LocalDate.now()

        // calculate the amount per installment
        val amountPerInstallment: Double = cart.value / numberOfInstallments

        // create a sequence of installments, one month apart from each other
        for (i in 1..numberOfInstallments) {
            // +1 to the month
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1)

            // create and persist the installment
            val newInstallment = Installment(nextInstallmentDueDate, amountPerInstallment)
            repository.persist(newInstallment)
        }
    }

    fun generateInstallments2(
        cart: ShoppingCart,
        numberOfInstallments: Int,
    ): List<Installment> {
        val generatedInstallments: MutableList<Installment> = ArrayList()

        // create a variable that will store the last installment date
        var nextInstallmentDueDate: LocalDate = LocalDate.now()

        // calculate the amount per installment
        val amountPerInstallment: Double = cart.value / numberOfInstallments

        // create a sequence of installments, one month apart from each other
        for (i in 1..numberOfInstallments) {
            // +1 to the month
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1)

            // create and persist the installment
            val newInstallment = Installment(nextInstallmentDueDate, amountPerInstallment)
            generatedInstallments.add(newInstallment)
            repository.persist(newInstallment)
        }

        return generatedInstallments
    }
}
