package com.alexey.targets2.domain.repository

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - Combines TaskReader and TaskWriter interfaces
 *    - Clients can choose to depend on specific interfaces (TaskReader/TaskWriter) or the full interface
 *    - Provides flexibility in dependency requirements
 * 
 * 2. LISKOV SUBSTITUTION PRINCIPLE (LSP):
 *    - Any implementation of TaskRepository can be substituted for TaskReader or TaskWriter
 *    - Maintains contract compatibility with both parent interfaces
 *    - Ensures type safety and polymorphism
 * 
 * 3. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Defines the complete abstraction for task operations
 *    - Domain layer depends on this abstraction, not concrete implementations
 *    - Enables dependency injection and testing
 */
interface TaskRepository : TaskReader, TaskWriter 