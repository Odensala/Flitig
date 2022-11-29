package com.odensala.flitig.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.odensala.flitig.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    class Callback @Inject constructor(
        private val todoDao: Provider<TodoDao>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = todoDao.get()

            applicationScope.launch {
                dao.insert(Todo("Take out the trash"))
                dao.insert(Todo("Vacuum", completed = true))
                dao.insert(Todo("Change guitar strings", important = true))
                dao.insert(Todo("Clean the windows"))
                dao.insert(Todo("Call grandma"))
                dao.insert(Todo("Buy 100 bags of chips"))
                dao.insert(Todo("Catch a squirrel"))
                dao.insert(Todo("Make korean BBQ"))
                dao.insert(Todo("Find a 100 year old coin"))
                dao.insert(Todo("Finish watching Onizuka"))
                dao.insert(Todo("Buy 1 bitcoin"))
                dao.insert(Todo("Buy 2 bitcoin"))
                dao.insert(Todo("Move to Japan"))
                dao.insert(Todo("Buy a GTR"))
                dao.insert(Todo("Bench 80kg"))
            }
        }
    }
}