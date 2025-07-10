package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.repository.TargetRepository
import com.alexey.targets2.domain.usecase.base.UnitUseCase
import javax.inject.Inject

data class ToggleTargetCompletionParams(
    val targetId: Long,
    val isCompleted: Boolean
)

class ToggleTargetCompletionUseCase @Inject constructor(
    private val repository: TargetRepository
) : UnitUseCase<ToggleTargetCompletionParams> {
    
    override suspend operator fun invoke(parameters: ToggleTargetCompletionParams) {
        repository.updateTargetCompletion(parameters.targetId, parameters.isCompleted)
    }
} 