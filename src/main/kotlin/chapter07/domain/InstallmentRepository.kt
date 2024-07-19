package me.bossm0n5t3r.chapter07.domain

interface InstallmentRepository {
    fun persist(installment: Installment)
}
