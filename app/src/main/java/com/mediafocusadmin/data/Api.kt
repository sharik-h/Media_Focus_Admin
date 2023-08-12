package com.mediafocusadmin.data

import com.mediafocusadmin.model.CompactPayments
import com.mediafocusadmin.model.Expense
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("jwt/getMyExp")
    suspend fun getAllExp(): List<Expense>

    @GET("jwt/getMyPayments")
    suspend fun getAllPayments(): List<CompactPayments>

    @POST("jwt/sendNewPayment")
    suspend fun sendNewPayment(): Boolean

    @POST("jwt/addNewExp")
    suspend fun addNewExp(
        @Query("desc") desc: String,
        @Query("amount") amount: Int
    ): Boolean

    @POST("jwt/updateExp")
    suspend fun updateExp(
        @Query("id") id: String,
        @Query("desc") desc: String,
        @Query("amount") amount: Int
    )

    @POST("jwt/addNewUser")
    suspend fun addNewUser(
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("date") date: String,
    ): Boolean

    @POST("jwt/updateUser")
    suspend fun updateUser(
        @Query("id") id: String,
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("date") date: String,
    )

}