package com.alexey.targets2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexey.targets2.domain.model.Priority
import com.alexey.targets2.domain.model.Task

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - This class has only one responsibility: representing a Task in the data layer
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
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null
) {
    /**
     * Maps data entity to domain model
     * Follows SRP: Single responsibility of data transformation
     */
    fun toDomain(): Task = Task(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        priority = priority,
        createdAt = createdAt,
        dueDate = dueDate
    )
    
    companion object {
        /**
         * Maps domain model to data entity
         * Follows SRP: Single responsibility of data transformation
         */
        fun fromDomain(task: Task): TaskEntity = TaskEntity(
            id = task.id,
            title = task.title,
            description = task.description,
            isCompleted = task.isCompleted,
            priority = task.priority,
            createdAt = task.createdAt,
            dueDate = task.dueDate
        )
    }
} 