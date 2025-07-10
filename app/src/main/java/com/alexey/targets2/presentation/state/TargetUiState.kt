package com.alexey.targets2.presentation.state

import com.alexey.targets2.domain.model.Target


data class TargetListState(
    val targets: List<Target> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showCompletedTargets: Boolean = false
) 