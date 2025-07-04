package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.model.Task
import com.alexey.targets2.domain.repository.TaskRepository
import com.alexey.targets2.domain.usecase.base.UnitUseCase
import javax.inject.Inject

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - This use case has only one responsibility: updating an existing task
 *    - Handles the business logic of task modification without mixing concerns
 *    - Clear, focused purpose
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    - Extends UnitUseCase without modifying the base interface
 *    - Can be extended with new validation logic without changing existing code
 *    - New update logic can be added without breaking existing usage
 * 
 * 3. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Depends on TaskRepository abstraction, not concrete implementation
 *    - Business logic is independent of data source implementation
 *    - Enables testing with mock repositories
 * 
 * 4. LISKOV SUBSTITUTION PRINCIPLE (LSP):
 *    - Can be substituted for UnitUseCase<Task> or UseCase<Task, Unit>
 *    - Maintains contract compatibility with parent interfaces
 *    - Provides type safety for operations without return values
 */
class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) : UnitUseCase<Task> {
    
    override suspend operator fun invoke(parameters: Task) {
        repository.updateTask(parameters)
    }
} 