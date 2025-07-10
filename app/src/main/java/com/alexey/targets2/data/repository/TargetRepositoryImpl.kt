package com.alexey.targets2.data.repository

import com.alexey.targets2.data.local.TargetDao
import com.alexey.targets2.domain.model.Target
import com.alexey.targets2.domain.repository.TargetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TargetRepositoryImpl @Inject constructor(
    private val targetDao: TargetDao
) : TargetRepository {
    
    override fun getAllTargets(): Flow<List<Target>> =
        targetDao.getAllTargets().map { entities -> entities.map { it.toDomain() } }
    
    override fun getActiveTargets(): Flow<List<Target>> =
        targetDao.getActiveTargets().map { entities -> entities.map { it.toDomain() } }
    
    override fun getCompletedTargets(): Flow<List<Target>> = 
        targetDao.getCompletedTargets().map { entities -> entities.map { it.toDomain() } }
    
    override suspend fun getTargetById(targetId: Long): Target? = 
        targetDao.getTargetById(targetId)?.toDomain()
    
    override suspend fun insertTarget(target: Target): Long = 
        targetDao.insertTarget(com.alexey.targets2.data.model.TargetEntity.fromDomain(target))
    
    override suspend fun updateTarget(target: Target) = 
        targetDao.updateTarget(com.alexey.targets2.data.model.TargetEntity.fromDomain(target))
    
    override suspend fun deleteTarget(target: Target) = 
        targetDao.deleteTarget(com.alexey.targets2.data.model.TargetEntity.fromDomain(target))
    
    override suspend fun deleteCompletedTargets() = targetDao.deleteCompletedTargets()
    
    override suspend fun updateTargetCompletion(targetId: Long, isCompleted: Boolean) = 
        targetDao.updateTargetCompletion(targetId, isCompleted)

    suspend fun updateTargetOrder(targetId: Long, order: Int) =
        targetDao.updateTargetOrder(targetId, order)
} 