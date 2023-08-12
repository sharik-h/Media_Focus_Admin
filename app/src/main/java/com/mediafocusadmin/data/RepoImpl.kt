package com.mediafocusadmin.data

import com.mediafocusadmin.model.CompactPayments
import com.mediafocusadmin.model.Expense
import javax.inject.Inject

class RepoImpl @Inject constructor(private val api: Api) : Repo {
    override suspend fun getAllExp(): List<Expense> {
        return api.getAllExp()
    }

    override suspend fun getAllPayments(): List<CompactPayments> {
        return api.getAllPayments()
    }

    override suspend fun sendNewPayment(): Boolean {
        return api.sendNewPayment()
    }

    override suspend fun addNewExp(desc: String, amount: Int): Boolean {
        return api.addNewExp(desc = desc, amount = amount)
    }

    override suspend fun updateExp(id: String, desc: String, amount: Int) {
        api.updateExp(id = id, desc = desc, amount = amount)
    }

    override suspend fun addNewUser(name: String, phone: String, email: String, date: String) {
        api.addNewUser(name = name, phone = phone, email = email, date = date)
    }

    override suspend fun updateUser(
        id: String,
        name: String,
        phone: String,
        email: String,
        date: String
    ) {
       api.updateUser(id = id, name = name, phone = phone, email = email, date = date)
    }
}