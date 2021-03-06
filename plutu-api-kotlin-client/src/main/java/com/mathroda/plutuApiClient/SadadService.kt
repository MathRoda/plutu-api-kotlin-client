package com.mathroda.plutuApiClient

import com.mathroda.plutuApiClient.entity.sadad.SadadEntity
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SadadService {

    @POST("v1/transaction/sadadapi/verify")
    suspend fun sendOtp(
        @Header("Authorization") token: String, // Bearer: [Access token]
        @Header("X-API-KEY") apiKey: String, // API Key
        @Query("mobile_number") mobileNumber: String, //Almadar mobile number 091XXXXXXX
        @Query("birth_year") birthYear: String, //4 digits XXXX
        @Query("amount") amount: String //Transaction amount in Libyan Dinar
        ): Response<SadadEntity>

    @POST("v1/transaction/sadadapi/confirm")
    suspend fun confirm(
        @Header("Authorization") token: String, // Bearer: [Access token]
        @Header("X-API-KEY") apiKey: String, // API Key
        @Query("process_id") processId: String, //Process ID is returned in the verify step
        @Query("code") otpCode: String, //OTP code is sent to customer's phone number
        @Query("amount") amount: String, //Transaction amount in Libyan Dinar
        @Query("invoice_no") invoiceNo: String, //Invoice number associated with transaction
        @Query("customer_ip") customerIp: String = "", //Customer IP address
    ): Response<SadadEntity>

}