package com.alexey.targets2.domain.repository

import com.alexey.targets2.domain.model.Target

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - This interface contains only write operations
 *    - Clients that only need to write Targets don't need to depend on read operations
 *    - Prevents forcing clients to implement methods they don't use
 * 
 * 2. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Defines abstraction for writing Targets
 *    - Higher-level modules depend on this abstraction, not concrete implementations
 *    - Allows for different implementations (local database, remote API, etc.)
 * 
 * 3. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - Interface has single responsibility: writing Target data
 *    - No read operations mixed with write operations
 */
interface TargetWriter {
    suspend fun insertTarget(target: Target): Long
    suspend fun updateTarget(target: Target)
    suspend fun deleteTarget(target: Target)
    suspend fun deleteCompletedTargets()
    suspend fun updateTargetCompletion(targetId: Long, isCompleted: Boolean)
} 