package com.example.weatherapp.util.exceptions

import com.google.gson.Gson
import okio.IOException
import retrofit2.HttpException

class ApiException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause)

object ApiExceptionHandler {
    fun handleException(throwable: Throwable): ApiException {
        return when (throwable) {
            is HttpException -> {
                val errorBody = throwable.response()?.errorBody()?.string()
                val errorJson = Gson().fromJson(errorBody, ErrorResponse::class.java)
                ApiException(errorJson?.error?.info ?: "Unknown API error")
            }
            is IOException -> ApiException("Network error", throwable)
            else -> ApiException("Unknown error", throwable)
        }
    }
}
