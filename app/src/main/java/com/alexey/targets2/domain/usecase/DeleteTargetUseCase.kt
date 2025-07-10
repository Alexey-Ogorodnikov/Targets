package com.alexey.targets2.domain.usecase

import com.alexey.targets2.domain.model.Target
import com.alexey.targets2.domain.repository.TargetRepository
import com.alexey.targets2.domain.usecase.base.UnitUseCase
import javax.inject.Inject

class DeleteTargetUseCase @Inject constructor(
    private val repository: TargetRepository
) : UnitUseCase<Target> {
    
    override suspend operator fun invoke(parameters: Target) {
        repository.deleteTarget(parameters)
    }
} 