package com.alexey.targets2.di

import com.alexey.targets2.data.repository.TargetRepositoryImpl
import com.alexey.targets2.domain.repository.TargetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTargetRepository(
        targetRepositoryImpl: TargetRepositoryImpl
    ): TargetRepository
} 