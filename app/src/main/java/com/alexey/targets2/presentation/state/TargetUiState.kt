package com.alexey.targets2.presentation.state

import com.alexey.targets2.domain.model.Target

//sealed class TargetUiState {
//    object Loading : TargetUiState()
//    data class Success(val Targets: List<Target>) : TargetUiState()
//    data class Error(val message: String) : TargetUiState()
//}

data class TargetListState(
    val targets: List<Target> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showCompletedTargets: Boolean = false
) 