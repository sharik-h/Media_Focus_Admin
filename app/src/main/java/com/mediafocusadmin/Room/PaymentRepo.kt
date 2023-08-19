package com.mediafocusadmin.Room

import com.mediafocusadmin.model.Payment

class PaymentRepo(private val db: PayRoomDatabase): PaymentDao {

    override suspend fun getAllPayments(): List<Payment> {
        return db.dao.getAllPayments()
    }

    override suspend fun addNewPayment(payments: Payment) {
        return db.dao.addNewPayment(payments)
    }

}