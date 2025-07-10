package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.model.Target
import com.alexey.targets2.domain.repository.TargetRepository
import com.alexey.targets2.domain.usecase.base.UseCase
import javax.inject.Inject

/**
 * SOLID PRINCIPLES APPLIED:
 * 
 * 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP):
 *    - This use case has only one responsibility: adding a new Target
 *    - Handles the business logic of Target creation without mixing concerns
 *    - Clear, focused purpose
 * 
 * 2. OPEN/CLOSED PRINCIPLE (OCP):
 *    - Extends UseCase without modifying the base interface
 *    - Can be extended with new validation logic without changing existing code
 *    - New parameters can be added to AddTargetParams without breaking existing usage
 * 
 * 3. DEPENDENCY INVERSION PRINCIPLE (DIP):
 *    - Depends on TargetRepository abstraction, not concrete implementation
 *    - Business logic is independent of data source implementation
 *    - Enables testing with mock repositories
 * 
 * 4. INTERFACE SEGREGATION PRINCIPLE (ISP):
 *    - Uses specific parameter class (AddTargetParams) instead of generic parameters
 *    - Clients only need to provide the parameters they actually use
 *    - Clear contract for what data is required to create a Target
 */
data class AddTargetParams(
    val title: String,
    val description: String,
    val priority: com.alexey.targets2.domain.model.Priority
)

class AddTargetUseCase @Inject constructor(
    private val repository: TargetRepository
) : UseCase<AddTargetParams, Long> {
    
    override suspend operator fun invoke(parameters: AddTargetParams): Long {
        val target = Target(
            title = parameters.title.trim(),
            description = parameters.description.trim(),
            priority = parameters.priority
        )
        return repository.insertTarget(target)
    }
} 