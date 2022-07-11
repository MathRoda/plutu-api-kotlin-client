package com.mathroda.plutuapiclient.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mathroda.plutuApiClient.entity.adfali.AdfaliEntity
import com.mathroda.plutuApiClient.entity.sadad.ErrorResponse
import com.mathroda.plutuApiClient.entity.sadad.SadadEntity
import com.mathroda.plutuapiclient.core.Constants
import com.mathroda.plutuapiclient.core.Resource
import com.mathroda.plutuapiclient.data.remote.InstancePlutuService
import kotlinx.coroutines.launch
import retrofit2.Response

class AdfaliViewModel: ViewModel() {

    private val _sendOtp = MutableLiveData<Resource<AdfaliEntity>>()
    val sendOtp: LiveData<Resource<AdfaliEntity>> = _sendOtp

    private val _confirm = MutableLiveData<Resource<AdfaliEntity>>()
    val confirm: LiveData<Resource<AdfaliEntity>> = _confirm


    /**
     * here, verifying user by sending OTP message
     */

    fun sendOtpFunction(
        mobileNumber: String,
        amount: String
    ) = viewModelScope.launch {
        try {
            _sendOtp.postValue(Resource.Loading())
            val response = InstancePlutuService.adfaliService
                .sendOtp(
                    token = Constants.TOKEN,
                    apiKey = Constants.API_KEY,
                    mobileNumber = mobileNumber,
                    amount = amount
                )
            handleSendOtpResponse(response)
        } catch (e: Exception) {
            _sendOtp.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun handleSendOtpResponse(response: Response<AdfaliEntity>) {
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
            val response = InstancePlutuService.adfaliService
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

    private fun handleConfirmResponse(response: Response<AdfaliEntity>) {
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