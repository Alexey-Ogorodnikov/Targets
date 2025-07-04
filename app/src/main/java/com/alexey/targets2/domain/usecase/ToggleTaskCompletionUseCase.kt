package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.repository.TaskRepository
import com.alexey.targets2.domain.usecase.base.UnitUseCase
import javax.inject.Inject

data class ToggleTaskCompletionParams(
    val taskId: Long,
    val isCompleted: Boolean
)

class ToggleTaskCompletionUseCase @Inject constructor(
    private val repository: TaskRepository
) : UnitUseCase<ToggleTaskCompletionParams> {
    
    override suspend operator fun invoke(parameters: ToggleTaskCompletionParams) {
        repository.updateTaskCompletion(parameters.taskId, parameters.isCompleted)
    }
} 