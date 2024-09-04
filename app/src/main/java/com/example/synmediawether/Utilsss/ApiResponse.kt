package com.example.synmediawether.Utilsss

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorType: ErrorType, val exception: Throwable? = null) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()

    sealed class ErrorType {
        object Network : ErrorType()
        object Server : ErrorType()
        object Unauthorized : ErrorType()
        object NotFound : ErrorType()
        object Unknown : ErrorType()
    }
}
