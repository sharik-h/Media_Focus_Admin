package com.mediafocusadmin.data

import com.mediafocusadmin.model.Expense
import com.mediafocusadmin.model.Payment
import com.mediafocusadmin.model.UserResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoImpl @Inject constructor(private val api: Api) : Repo {
    override suspend fun getAllExp(): List<Expense> {
        return withContext(Dispatchers.IO){
            api.getAllExp()
        }
    }

    override suspend fun getAllPayments(): List<Payment> {
        return withContext(Dispatchers.IO){
            api.getAllPayments()
        }
    }

    override suspend fun sendNewPayment(): Boolean {
        return withContext(Dispatchers.IO){
            api.sendNewPayment()
        }
    }

    override suspend fun addNewExp(id: Int,desc: String, amount: String, date: String) {
        withContext(Dispatchers.IO){
            api.addNewExp(id = id, desc = desc, amount = amount, date = date)
        }
    }

    override suspend fun updateExp(id: Int, desc: String, amount: String, date: String) {
        withContext(Dispatchers.IO){
            api.updateExp(id = id, desc = desc, amount = amount, date = date)
        }
    }

    override suspend fun addNewUser(userId: String, name: String, phone: String, date: String, plan: String) {
        withContext(Dispatchers.IO){
            api.addNewUser(userId = userId, name = name, phone = phone, date = date, plan = plan)
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

    override suspend fun deleteExp(id: Int): Boolean {
        return withContext(Dispatchers.IO){
            api.deleteExp(id = id).toString() == "1"
        }
    }

    override suspend fun getUnRegUsers(): UserResult {
        return withContext(Dispatchers.IO){
            try {
                UserResult.Success(api.getUnRegUsers())
            }catch (e: Exception) {
                UserResult.Error(e.message.toString())
            }
        }
    }

    override suspend fun getAllRegUsers(): UserResult {
        return withContext(Dispatchers.IO){
            try {
                UserResult.Success(api.getAllRegUsers())
            }catch (e: Exception) {
                UserResult.Error(e.message.toString())
            }
        }
    }
}