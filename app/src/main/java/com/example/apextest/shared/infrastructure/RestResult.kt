package com.example.apextest.shared.infrastructure

sealed class RestResult<out T>{
    data class Success<T>(val data: T): RestResult<T>()
    data class Error(
        val message: String?,
        val httpCode: Int
    ): RestResult<Nothing>()
}
