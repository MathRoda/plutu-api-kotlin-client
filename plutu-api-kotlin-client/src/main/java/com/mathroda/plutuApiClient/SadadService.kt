package com.mathroda.plutuApiClient

import com.mathroda.plutuApiClient.entity.sadad.SadadEntity
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SadadService {
    @POST("transaction/sadadapi/verify")
    suspend fun sendOtp(
        @Header("Authorization") token: String, // Bearer: [Access token]
        @Header("X-API-KEY") apiKey: String, // API Key
        @Query("mobile_number") mobileNumber: String, //Almadar mobile number 091XXXXXXX
        @Query("birth_year") birthYear: String, //4 digits XXXX
        @Query("amount") amount: String, //Transaction amount in Libyan Dinar
        ): Response<SadadEntity>
}