package com.alexey.targets2.di

import android.content.Context
import com.alexey.targets2.data.local.AppDatabase
import com.alexey.targets2.data.local.TargetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideTargetDao(database: AppDatabase): TargetDao {
        return database.targetDao()
    }
} 