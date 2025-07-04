package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.model.Task
import com.alexey.targets2.domain.repository.TaskRepository
import com.alexey.targets2.domain.usecase.base.UseCase
import javax.inject.Inject

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - This use case has only one responsibility: adding a new task
 *    - Handles the business logic of task creation without mixing concerns
 *    - Clear, focused purpose
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    - Extends UseCase without modifying the base interface
 *    - Can be extended with new validation logic without changing existing code
 *    - New parameters can be added to AddTaskParams without breaking existing usage
 * 
 * 3. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Depends on TaskRepository abstraction, not concrete implementation
 *    - Business logic is independent of data source implementation
 *    - Enables testing with mock repositories
 * 
 * 4. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - Uses specific parameter class (AddTaskParams) instead of generic parameters
 *    - Clients only need to provide the parameters they actually use
 *    - Clear contract for what data is required to create a task
 */
data class AddTaskParams(
    val title: String,
    val description: String,
    val priority: com.alexey.targets2.domain.model.Priority
)

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) : UseCase<AddTaskParams, Long> {
    
    override suspend operator fun invoke(parameters: AddTaskParams): Long {
        val task = Task(
            title = parameters.title.trim(),
            description = parameters.description.trim(),
            priority = parameters.priority
        )
        return repository.insertTask(task)
    }
} 