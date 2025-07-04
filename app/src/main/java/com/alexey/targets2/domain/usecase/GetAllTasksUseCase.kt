package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.model.Task
import com.alexey.targets2.domain.repository.TaskRepository
import com.alexey.targets2.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - This use case has only one responsibility: getting tasks based on completion status
 *    - Handles the business logic of filtering tasks without mixing concerns
 *    - Clear, focused purpose
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    - Extends FlowUseCase without modifying the base interface
 *    - Can be extended with new filtering logic without changing existing code
 *    - New parameters can be added to GetAllTasksParams without breaking existing usage
 * 
 * 3. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Depends on TaskRepository abstraction, not concrete implementation
 *    - Business logic is independent of data source implementation
 *    - Enables testing with mock repositories
 * 
 * 4. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - Uses specific parameter class (GetAllTasksParams) instead of generic parameters
 *    - Clients only need to provide the parameters they actually use
 *    - Clear contract for what data is required
 */
data class GetAllTasksParams(val showCompleted: Boolean = false)

class GetAllTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) : FlowUseCase<GetAllTasksParams, List<Task>> {
    
    override operator fun invoke(parameters: GetAllTasksParams): Flow<List<Task>> {
        return if (parameters.showCompleted) {
            repository.getAllTasks()
        } else {
            repository.getActiveTasks()
        }
    }
} 