package com.mediafocusadmin.data

import com.mediafocusadmin.model.Expense
import com.mediafocusadmin.model.Payment
import com.mediafocusadmin.model.User
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("/getAllExpense")
    suspend fun getAllExp(): List<Expense>

    @GET("/getAllPayment")
    suspend fun getAllPayments(): List<Payment>

    @POST("/sendNewPayment")
    suspend fun sendNewPayment(): Boolean

    @POST("/addNewExp")
    suspend fun addNewExp(
        @Query("id") id: Int,
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

    @POST("/updateAUser")
    suspend fun addNewUser(
        @Query("userId") userId: String,
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("date") date: String,
        @Query("plan") plan: String
    )

    @POST("/updateAUser")
    suspend fun updateUser(
        @Query("id") id: Int,
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("date") date: String,
    )

    @DELETE("/removeExp")
    suspend fun deleteExp(@Query("id") id: Int): Int

    @GET("getAllUnRegUsers")
    suspend fun getUnRegUsers(): List<User>

    @GET("getAllRegUsers")
    suspend fun getAllRegUsers(): List<User>

}