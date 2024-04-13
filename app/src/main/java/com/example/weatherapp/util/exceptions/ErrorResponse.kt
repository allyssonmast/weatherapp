package com.example.weatherapp.util.exceptions

data class ErrorResponse(
    val success: Boolean,
    val error: ApiError
)

data class ApiError(
    val code: Int,
    val type: String,
    val info: String
)
