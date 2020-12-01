package com.mvvm.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mvvm.todo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().taskDao()
            applicationScope.launch {
                dao.insert(Task("Look for motivation",completed = true))
                dao.insert(Task("Pick a problem to solve"))
                dao.insert(Task("Organize",important = true))
                dao.insert(Task("Priotize"))
                dao.insert(Task("Execute"))
                dao.insert(Task("Repeat and Conquer"))
            }
        }
    }
}