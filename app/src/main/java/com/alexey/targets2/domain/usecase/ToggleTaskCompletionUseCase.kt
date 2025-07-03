package com.alexey.targets2.domain.usecase

import com.alexey.targets2.data.repository.TaskRepository
import javax.inject.Inject

class ToggleTaskCompletionUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskId: Long, isCompleted: Boolean) = 
        repository.updateTaskCompletion(taskId, isCompleted)
} 