package com.mathroda.plutuapiclient.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mathroda.plutuApiClient.entity.sadad.ErrorResponse
import com.mathroda.plutuApiClient.entity.sadad.SadadEntity
import com.mathroda.plutuapiclient.core.Constants
import com.mathroda.plutuapiclient.core.Resource
import com.mathroda.plutuapiclient.data.remote.InstancePlutuService
import kotlinx.coroutines.launch
import retrofit2.Response

class SadadViewModel: ViewModel() {

    private val _sendOtp = MutableLiveData<Resource<SadadEntity>>()
    val sendOtp: LiveData<Resource<SadadEntity>> = _sendOtp

    private val _confirm = MutableLiveData<Resource<SadadEntity>>()
    val confirm: LiveData<Resource<SadadEntity>> = _confirm


    /**
     * here, verifying user by sending OTP message
     */

    fun sendOtpFunction(
        mobileNumber: String,
        birthYear: String,
        amount: String
    ) = viewModelScope.launch {
        try {
            _sendOtp.postValue(Resource.Loading())
            val response = InstancePlutuService.sadadService
                .sendOtp(
                    token = Constants.TOKEN,
                    apiKey = Constants.API_KEY,
                    mobileNumber = mobileNumber,
                    birthYear = birthYear,
                    amount = amount
                )
            handleSendOtpResponse(response)
        } catch (e: Exception) {
            _sendOtp.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun handleSendOtpResponse(response: Response<SadadEntity>) {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                _sendOtp.postValue(Resource.Success(result))
            }
        } else {
           _sendOtp.postValue(Resource.ErrorResult(getErrorResponse(response.errorBody()!!.string())))
        }
    }


    /**
     * here, confirming payments by insert the OTP Code you received
     */

     fun confirmFunction(
        processId: String,
        otpCode: String,
        amount: String,
        invoiceNo: String
    ) = viewModelScope.launch {
        try {
            _sendOtp.postValue(Resource.Loading())
            val response = InstancePlutuService.sadadService
                .confirm(
                    token = Constants.TOKEN,
                    apiKey = Constants.API_KEY,
                    processId = processId,
                    otpCode = otpCode,
                    amount = amount,
                    invoiceNo = invoiceNo
                )
            handleConfirmResponse(response)

        } catch (e: Exception) {
            _sendOtp.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun handleConfirmResponse(response: Response<SadadEntity>) {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                _confirm.postValue(Resource.Success(result))
            }
        } else {
            _confirm.postValue(Resource.ErrorResult(getErrorResponse(response.errorBody()!!.string())))
        }
    }


    // handling error response
    private fun getErrorResponse(response:String): ErrorResponse {
        return Gson().fromJson(response, ErrorResponse::class.java)

    }
}