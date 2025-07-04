package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.model.Task
import com.alexey.targets2.domain.repository.TaskRepository
import com.alexey.targets2.domain.usecase.base.UnitUseCase
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) : UnitUseCase<Task> {
    
    override suspend operator fun invoke(parameters: Task) {
        repository.deleteTask(parameters)
    }
} 