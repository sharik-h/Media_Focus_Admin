package com.mediafocusadmin.Room

import com.mediafocusadmin.model.CompactPayments

class PaymentRepo(private val db: PayRoomDatabase): PaymentDao {

    override suspend fun getAllPayments(): List<CompactPayments> {
        return db.dao.getAllPayments()
    }

    override suspend fun addNewPayment(payments: CompactPayments) {
        return db.dao.addNewPayment(payments)
    }

}