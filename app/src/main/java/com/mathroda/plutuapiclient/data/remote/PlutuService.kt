package com.mathroda.plutuapiclient.data.remote

import com.mathroda.plutuApiClient.AdfaliService
import com.mathroda.plutuApiClient.BaseUrl
import com.mathroda.plutuApiClient.SadadService
import com.mathroda.plutuapiclient.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val retrofitBuilder =  Retrofit.Builder()
    .baseUrl(BaseUrl.PLUTU_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


object InstancePlutuService {
    val sadadService: SadadService by lazy {
        retrofitBuilder.create()
    }

    val adfaliService: AdfaliService by lazy {
        retrofitBuilder.create()
    }
}