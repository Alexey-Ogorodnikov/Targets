package com.alexey.targets2.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexey.targets2.R
import com.alexey.targets2.domain.model.Task
import com.alexey.targets2.domain.usecase.*
import com.alexey.targets2.presentation.state.TaskListState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val toggleTaskCompletionUseCase: ToggleTaskCompletionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TaskListState())
    val state: StateFlow<TaskListState> = _state.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            getAllTasksUseCase(GetAllTasksParams(_state.value.showCompletedTasks))
                .catch { exception ->
                    _state.update { 
                        it.copy(
                            isLoading = false, 
                            error = exception.message ?: context.getString(R.string.unknown_error_occurred)
                        )
                    }
                }
                .collect { tasks ->
                    _state.update { 
                        it.copy(
                            tasks = tasks,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun addTask(title: String, description: String, priority: com.alexey.targets2.domain.model.Priority) {
        if (title.isBlank()) return
        
        viewModelScope.launch {
            try {
                val params = AddTaskParams(title, description, priority)
                addTaskUseCase(params)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_add_target))
                }
            }
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            try {
                val params = ToggleTaskCompletionParams(task.id, !task.isCompleted)
                toggleTaskCompletionUseCase(params)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_update_target))
                }
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                deleteTaskUseCase(task)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_delete_target))
                }
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                updateTaskUseCase(task)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_update_target))
                }
            }
        }
    }

    fun toggleShowCompletedTasks() {
        _state.update { it.copy(showCompletedTasks = !it.showCompletedTasks) }
        loadTasks()
    }

    fun clearError() {
        _state.update { it.copy(error = null) }
    }
} 