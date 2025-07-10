package com.alexey.targets2.domain.exception

sealed class TargetException(message: String) : Exception(message) {
    class TargetNotFound(id: Long) : TargetException("Target with id $id not found")
    class InvalidTargetData(message: String) : TargetException("Invalid target data: $message")
    class DatabaseError(message: String) : TargetException("Database error: $message")
    class NetworkError(message: String) : TargetException("Network error: $message")
} 