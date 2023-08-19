package com.mediafocusadmin.Room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mediafocusadmin.model.Payment

@Dao
interface PaymentDao {

    @Query("SELECT * FROM  Payment  ORDER  BY id ASC ")
    suspend fun getAllPayments(): List<Payment>

    @Upsert
    suspend fun addNewPayment(payments: Payment)

}