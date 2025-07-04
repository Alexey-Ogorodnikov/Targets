package com.alexey.targets2.domain.result

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

sealed class TaskResult<out T> : Result<T>() {
    data class TaskSuccess<T>(val data: T) : TaskResult<T>()
    data class TaskError(val message: String) : TaskResult<Nothing>()
    object TaskLoading : TaskResult<Nothing>()
} 