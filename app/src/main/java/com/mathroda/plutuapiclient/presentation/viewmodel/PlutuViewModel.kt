package com.mathroda.plutuapiclient.presentation.viewmodel

import androidx.lifecycle.ViewModel

class PlutuViewModel: ViewModel() {

    /*private val _sendOtp = MutableLiveData<Resource<com.mathroda.plutuApiClient.entity.sadad.SadadEntity>>()
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
    }*/
}