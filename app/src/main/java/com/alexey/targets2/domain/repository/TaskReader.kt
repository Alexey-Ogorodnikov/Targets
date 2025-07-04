package com.alexey.targets2.domain.repository

import com.alexey.targets2.domain.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - This interface contains only read operations
 *    - Clients that only need to read tasks don't need to depend on write operations
 *    - Prevents forcing clients to implement methods they don't use
 * 
 * 2. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Defines abstraction for reading tasks
 *    - Higher-level modules depend on this abstraction, not concrete implementations
 *    - Allows for different implementations (local database, remote API, etc.)
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - Interface has single responsibility: reading task data
 *    - No write operations mixed with read operations
 */
interface TaskReader {
    fun getAllTasks(): Flow<List<Task>>
    fun getActiveTasks(): Flow<List<Task>>
    fun getCompletedTasks(): Flow<List<Task>>
    suspend fun getTaskById(taskId: Long): Task?
} 