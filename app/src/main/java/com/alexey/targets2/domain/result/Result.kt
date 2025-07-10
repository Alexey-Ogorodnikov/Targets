package com.alexey.targets2.domain.result

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

sealed class TargetResult<out T> : Result<T>() {
    data class TargetSuccess<T>(val data: T) : TargetResult<T>()
    data class TargetError(val message: String) : TargetResult<Nothing>()
    object TargetLoading : TargetResult<Nothing>()
} 