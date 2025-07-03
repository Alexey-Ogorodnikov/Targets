package com.alexey.targets2.domain.usecase

import com.alexey.targets2.data.model.Task
import com.alexey.targets2.data.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = repository.deleteTask(task)
} 