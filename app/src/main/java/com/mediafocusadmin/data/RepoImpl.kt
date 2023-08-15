package com.mediafocusadmin.data

import com.mediafocusadmin.model.CompactPayments
import com.mediafocusadmin.model.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoImpl @Inject constructor(private val api: Api) : Repo {
    override suspend fun getAllExp(): List<Expense> {
        return withContext(Dispatchers.IO){
            api.getAllExp()
        }
    }

    override suspend fun getAllPayments(): List<CompactPayments> {
        return withContext(Dispatchers.IO){
            api.getAllPayments()
        }
    }

    override suspend fun sendNewPayment(): Boolean {
        return withContext(Dispatchers.IO){
            api.sendNewPayment()
        }
    }

    override suspend fun addNewExp(desc: String, amount: String, date: String) {
        withContext(Dispatchers.IO){
            api.addNewExp(desc = desc, amount = amount, date = date)
        }
    }

    override suspend fun updateExp(id: Int, desc: String, amount: String, date: String) {
        withContext(Dispatchers.IO){
            api.updateExp(id = id, desc = desc, amount = amount, date = date)
        }
    }

    override suspend fun addNewUser(name: String, phone: String, email: String, date: String) {
        withContext(Dispatchers.IO){
            api.addNewUser(name = name, phone = phone, email = email, date = date)
        }
    }

    override suspend fun updateUser(
        id: Int,
        name: String,
        phone: String,
        email: String,
        date: String
    ) {
        withContext(Dispatchers.IO){
            api.updateUser(id = id, name = name, phone = phone, email = email, date = date)
        }
    }
}