package com.mathroda.plutuapiclient.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathroda.plutuapiclient.core.Constants
import com.mathroda.plutuapiclient.core.Resource
import com.mathroda.plutuapiclient.data.remote.InstancePlutuService
import com.mathroda.plutuApiClient.entity.sadad.SadadEntity
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class PlutuViewModel: ViewModel() {

    private val _sendOtp = MutableLiveData<Resource<com.mathroda.plutuApiClient.entity.sadad.SadadEntity>>()
    val sendOtp: LiveData<Resource<com.mathroda.plutuApiClient.entity.sadad.SadadEntity>> = _sendOtp

    init {
        sendOtpFunction()
    }

    private fun sendOtpFunction() = viewModelScope.launch {
        try {
            _sendOtp.postValue(Resource.Loading())
            val response = InstancePlutuService.plutuService
                .sendOtp(
                    token = Constants.TOKEN,
                    apiKey = Constants.API_KEY,
                    mobileNumber = "0913632323",
                    birthYear = "2000",
                    amount = "750"
                )
            handleSendOtpResponse(response)
        } catch (e: Exception) {
            _sendOtp.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun handleSendOtpResponse(response: Response<com.mathroda.plutuApiClient.entity.sadad.SadadEntity>) {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                _sendOtp.postValue(Resource.Success(result))
            }
        } else {
           Log.d("error", response.message())
        }
    }
}