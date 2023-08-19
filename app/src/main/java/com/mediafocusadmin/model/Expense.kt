package com.mediafocusadmin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var desc: String,
    var amount: String,
    var date: String
)