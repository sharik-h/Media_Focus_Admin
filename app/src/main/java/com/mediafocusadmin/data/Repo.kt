package com.mediafocusadmin.data

import com.mediafocusadmin.model.Expense
import com.mediafocusadmin.model.Payment
import com.mediafocusadmin.model.UserResult

interface Repo {
    suspend fun getAllExp(): List<Expense>
    suspend fun getAllPayments(): List<Payment>
    suspend fun sendNewPayment(): Boolean
    suspend fun addNewExp(id: Int, desc: String, amount: String, date: String)
    suspend fun updateExp(id: Int, desc: String, amount: String, date: String)
    suspend fun addNewUser(userId: String, name: String, phone: String, date: String, plan: String)
    suspend fun updateUser(id: Int, name: String, phone: String, email: String, date: String)
    suspend fun deleteExp(id: Int): Boolean
    suspend fun getUnRegUsers(): UserResult
    suspend fun getAllRegUsers(): UserResult
}