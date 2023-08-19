package com.mediafocusadmin.Room

import com.mediafocusadmin.model.Expense

class ExpenseRepo(private val db: RoomDatabases): ExpenseDao {

    override suspend fun getAllExp(): List<Expense> {
        return db.dao.getAllExp()
    }

    override suspend fun addNewExp(expense: Expense) {
        db.dao.addNewExp(expense)
    }

    override suspend fun deleteExp(expense: Expense) {
        db.dao.deleteExp(expense)
    }

}