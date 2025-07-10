package com.alexey.targets2.data.local

import androidx.room.*
import com.alexey.targets2.data.model.TargetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TargetDao {
    
    @Query("SELECT * FROM targets ORDER BY `order` ASC, createdAt DESC")
    fun getAllTargets(): Flow<List<TargetEntity>>
    
    @Query("SELECT * FROM targets WHERE isCompleted = 0 ORDER BY `order` ASC, createdAt DESC")
    fun getActiveTargets(): Flow<List<TargetEntity>>
    
    @Query("SELECT * FROM targets WHERE isCompleted = 1 ORDER BY `order` ASC, createdAt DESC")
    fun getCompletedTargets(): Flow<List<TargetEntity>>
    
    @Query("SELECT * FROM targets WHERE id = :targetId")
    suspend fun getTargetById(targetId: Long): TargetEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTarget(target: TargetEntity): Long
    
    @Update
    suspend fun updateTarget(target: TargetEntity)
    
    @Delete
    suspend fun deleteTarget(target: TargetEntity)
    
    @Query("DELETE FROM targets WHERE isCompleted = 1")
    suspend fun deleteCompletedTargets()
    
    @Query("UPDATE targets SET isCompleted = :isCompleted WHERE id = :targetId")
    suspend fun updateTargetCompletion(targetId: Long, isCompleted: Boolean)
    
    @Query("UPDATE targets SET `order` = :order WHERE id = :targetId")
    suspend fun updateTargetOrder(targetId: Long, order: Int)
} 