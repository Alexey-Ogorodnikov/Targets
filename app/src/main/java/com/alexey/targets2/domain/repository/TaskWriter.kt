package com.alexey.targets2.domain.repository

import com.alexey.targets2.domain.model.Task

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - This interface contains only write operations
 *    - Clients that only need to write tasks don't need to depend on read operations
 *    - Prevents forcing clients to implement methods they don't use
 * 
 * 2. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Defines abstraction for writing tasks
 *    - Higher-level modules depend on this abstraction, not concrete implementations
 *    - Allows for different implementations (local database, remote API, etc.)
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - Interface has single responsibility: writing task data
 *    - No read operations mixed with write operations
 */
interface TaskWriter {
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteCompletedTasks()
    suspend fun updateTaskCompletion(taskId: Long, isCompleted: Boolean)
} 