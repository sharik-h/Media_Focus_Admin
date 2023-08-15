package com.mediafocusadmin.data

import com.mediafocusadmin.model.CompactPayments
import com.mediafocusadmin.model.Expense

interface Repo {
    suspend fun getAllExp(): List<Expense>
    suspend fun getAllPayments(): List<CompactPayments>
    suspend fun sendNewPayment(): Boolean
    suspend fun addNewExp(desc: String, amount: String, date: String)
    suspend fun updateExp(id: Int, desc: String, amount: String, date: String)
    suspend fun addNewUser(name: String, phone: String, email: String, date: String)
    suspend fun updateUser(id: Int, name: String, phone: String, email: String, date: String)
}