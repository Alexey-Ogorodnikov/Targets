package com.alexey.targets2.data.repository

import com.alexey.targets2.data.local.TaskDao
import com.alexey.targets2.data.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    
    override fun getActiveTasks(): Flow<List<Task>> = taskDao.getActiveTasks()
    
    override fun getCompletedTasks(): Flow<List<Task>> = taskDao.getCompletedTasks()
    
    override suspend fun getTaskById(taskId: Long): Task? = taskDao.getTaskById(taskId)
    
    override suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)
    
    override suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    
    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    
    override suspend fun deleteCompletedTasks() = taskDao.deleteCompletedTasks()
    
    override suspend fun updateTaskCompletion(taskId: Long, isCompleted: Boolean) = 
        taskDao.updateTaskCompletion(taskId, isCompleted)
} 