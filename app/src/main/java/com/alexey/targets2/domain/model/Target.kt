package com.alexey.targets2.domain.model

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - This class has only one responsibility: representing a Target in the domain layer
 *    - Contains only business logic and data, no framework-specific annotations
 *    - Separated from data layer concerns (no Room annotations)
 * 
 * 2. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Domain models are framework-agnostic and don't depend on external libraries
 *    - Higher-level modules (use cases, repositories) depend on this abstraction
 *    - Data layer adapts to this domain model, not vice versa
 */
data class Target(
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null,
    val order: Int = 0
)

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - Enum has single responsibility: defining Target priority levels
 *    - No business logic, just data representation
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    - Can be extended with new priority levels without modifying existing code
 *    - Used throughout the application without requiring changes to consuming classes
 */
enum class Priority {
    LOW, MEDIUM, HIGH
} 