package com.mediafocusadmin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String,
    val amount: Int,
    val date: String,
    val status: Boolean
)