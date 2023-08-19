package com.mediafocusadmin.Room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mediafocusadmin.model.CompactPayments

@Dao
interface PaymentDao {

    @Query("SELECT * FROM  CompactPayments  ORDER  BY id ASC ")
    suspend fun getAllPayments(): List<CompactPayments>

    @Upsert
    suspend fun addNewPayment(payments: CompactPayments)

}