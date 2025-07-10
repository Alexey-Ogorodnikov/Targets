package com.alexey.targets2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexey.targets2.domain.model.Priority
import com.alexey.targets2.domain.model.Target

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - This class has only one responsibility: representing a Target in the data layer
 *    - Contains only database-specific annotations and mapping logic
 *    - Separated from domain business logic
 * 
 * 2. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Implements mapping functions to convert between domain and data models
 *    - Data layer adapts to domain model, not the other way around
 *    - Domain model remains independent of data layer concerns
 * 
 * 3. OPEN/CLOSED PRINCIPLE (OCP):
 *    - Mapping functions can be extended without modifying existing code
 *    - New conversion methods can be added without changing the core entity
 */
@Entity(tableName = "targets")
data class TargetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null,
    val order: Int = 0
) {
    /**
     * Maps data entity to domain model
     * Follows SRP: Single responsibility of data transformation
     */
    fun toDomain(): Target = Target(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        priority = priority,
        createdAt = createdAt,
        dueDate = dueDate,
        order = order
    )
    
    companion object {
        /**
         * Maps domain model to data entity
         * Follows SRP: Single responsibility of data transformation
         */
        fun fromDomain(target: Target): TargetEntity = TargetEntity(
            id = target.id,
            title = target.title,
            description = target.description,
            isCompleted = target.isCompleted,
            priority = target.priority,
            createdAt = target.createdAt,
            dueDate = target.dueDate,
            order = target.order
        )
    }
} 