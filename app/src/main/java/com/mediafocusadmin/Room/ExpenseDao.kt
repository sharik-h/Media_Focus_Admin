package com.mediafocusadmin.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mediafocusadmin.model.Expense

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense ORDER BY id DESC")
    suspend fun getAllExp(): List<Expense>

    @Upsert
    suspend fun addNewExp(expense: Expense)

    @Delete
    suspend fun deleteExp(expense: Expense)

}