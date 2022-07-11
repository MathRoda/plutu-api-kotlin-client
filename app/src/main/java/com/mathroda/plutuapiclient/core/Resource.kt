package com.mathroda.plutuapiclient.core

import com.mathroda.plutuApiClient.entity.sadad.ErrorResponse

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorResponse: ErrorResponse? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class ErrorResult<T>(errorResponse: ErrorResponse?) : Resource<T>(null, null, errorResponse)
}