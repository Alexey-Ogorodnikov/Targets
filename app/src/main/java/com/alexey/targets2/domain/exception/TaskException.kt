package com.alexey.targets2.domain.exception

sealed class TaskException(message: String) : Exception(message) {
    class TaskNotFound(id: Long) : TaskException("Task with id $id not found")
    class InvalidTaskData(message: String) : TaskException("Invalid task data: $message")
    class DatabaseError(message: String) : TaskException("Database error: $message")
    class NetworkError(message: String) : TaskException("Network error: $message")
} 