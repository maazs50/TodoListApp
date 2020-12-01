package com.mvvm.todo.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.todo.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): TaskDatabase {
       val obj = Room.databaseBuilder(app, TaskDatabase::class.java,"task_database")
            .fallbackToDestructiveMigration()
            .build()
        return obj
    }

    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()
}