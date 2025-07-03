package com.alexey.targets2.domain.usecase

import com.alexey.targets2.data.model.Task
import com.alexey.targets2.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = repository.getAllTasks()
} 