package com.mathroda.plutuapiclient.data.remote

import com.mathroda.plutuapiclient.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofitBuilder =  Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


object InstancePlutuService {
    val plutuService: com.mathroda.plutuApiClient.PlutuApi by lazy {
        retrofitBuilder.create(com.mathroda.plutuApiClient.PlutuApi::class.java)
    }
}