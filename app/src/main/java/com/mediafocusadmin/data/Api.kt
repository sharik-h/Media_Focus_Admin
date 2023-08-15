package com.mediafocusadmin.data

import com.mediafocusadmin.model.CompactPayments
import com.mediafocusadmin.model.Expense
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("/getAllExpense")
    suspend fun getAllExp(): List<Expense>

    @GET("/getAllPayment")
    suspend fun getAllPayments(): List<CompactPayments>

    @POST("sendNewPayment")
    suspend fun sendNewPayment(): Boolean

    @POST("/addNewExp")
    suspend fun addNewExp(
        @Query("desc") desc: String,
        @Query("amount") amount: String,
        @Query("date") date: String
    )

    @POST("/updateExp")
    suspend fun updateExp(
        @Query("id") id: Int,
        @Query("desc") desc: String,
        @Query("amount") amount: String,
        @Query("date") date: String
    )

    @POST("/addNewUser")
    suspend fun addNewUser(
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("date") date: String,
    )

    @POST("/updateAUser")
    suspend fun updateUser(
        @Query("id") id: Int,
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("date") date: String,
    )

}