package com.alexey.targets2.data.repository

import com.alexey.targets2.data.local.TaskDao
import com.alexey.targets2.domain.model.Task
import com.alexey.targets2.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    
    override fun getAllTasks(): Flow<List<Task>> = 
        taskDao.getAllTasks().map { entities -> entities.map { it.toDomain() } }
    
    override fun getActiveTasks(): Flow<List<Task>> = 
        taskDao.getActiveTasks().map { entities -> entities.map { it.toDomain() } }
    
    override fun getCompletedTasks(): Flow<List<Task>> = 
        taskDao.getCompletedTasks().map { entities -> entities.map { it.toDomain() } }
    
    override suspend fun getTaskById(taskId: Long): Task? = 
        taskDao.getTaskById(taskId)?.toDomain()
    
    override suspend fun insertTask(task: Task): Long = 
        taskDao.insertTask(com.alexey.targets2.data.model.TaskEntity.fromDomain(task))
    
    override suspend fun updateTask(task: Task) = 
        taskDao.updateTask(com.alexey.targets2.data.model.TaskEntity.fromDomain(task))
    
    override suspend fun deleteTask(task: Task) = 
        taskDao.deleteTask(com.alexey.targets2.data.model.TaskEntity.fromDomain(task))
    
    override suspend fun deleteCompletedTasks() = taskDao.deleteCompletedTasks()
    
    override suspend fun updateTaskCompletion(taskId: Long, isCompleted: Boolean) = 
        taskDao.updateTaskCompletion(taskId, isCompleted)
} 