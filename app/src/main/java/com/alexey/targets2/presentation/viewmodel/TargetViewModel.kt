package com.alexey.targets2.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexey.targets2.R
import com.alexey.targets2.domain.usecase.*
import com.alexey.targets2.presentation.state.TargetListState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.alexey.targets2.domain.model.Target

@HiltViewModel
class TargetViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getAllTargetsUseCase: GetAllTargetsUseCase,
    private val addTargetUseCase: AddTargetUseCase,
    private val updateTargetUseCase: UpdateTargetUseCase,
    private val deleteTargetUseCase: DeleteTargetUseCase,
    private val toggleTargetCompletionUseCase: ToggleTargetCompletionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TargetListState())
    val state: StateFlow<TargetListState> = _state.asStateFlow()

    init {
        loadTargets()
    }

    private fun loadTargets() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            getAllTargetsUseCase(GetAllTargetsParams(_state.value.showCompletedTargets))
                .catch { exception ->
                    _state.update { 
                        it.copy(
                            isLoading = false, 
                            error = exception.message ?: context.getString(R.string.unknown_error_occurred)
                        )
                    }
                }
                .collect { targets ->
                    _state.update { 
                        it.copy(
                            targets = targets,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun addTarget(title: String, description: String, priority: com.alexey.targets2.domain.model.Priority) {
        if (title.isBlank()) return
        
        viewModelScope.launch {
            try {
                val params = AddTargetParams(title, description, priority)
                addTargetUseCase(params)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_add_target))
                }
            }
        }
    }

    fun toggleTargetCompletion(target: Target) {
        viewModelScope.launch {
            try {
                val params = ToggleTargetCompletionParams(target.id, !target.isCompleted)
                toggleTargetCompletionUseCase(params)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_update_target))
                }
            }
        }
    }

    fun deleteTarget(target: Target) {
        viewModelScope.launch {
            try {
                deleteTargetUseCase(target)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_delete_target))
                }
            }
        }
    }

    fun updateTarget(target: Target) {
        viewModelScope.launch {
            try {
                updateTargetUseCase(target)
            } catch (e: Exception) {
                _state.update { 
                    it.copy(error = e.message ?: context.getString(R.string.failed_to_update_target))
                }
            }
        }
    }

    fun toggleShowCompletedTargets() {
        _state.update { it.copy(showCompletedTargets = !it.showCompletedTargets) }
        loadTargets()
    }

    fun clearError() {
        _state.update { it.copy(error = null) }
    }
} 