package com.example.typeracer.data

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [WPM::class], version = 2)
abstract class WPMRoomDatabase : RoomDatabase() {

    abstract fun wpmDao(): WPMDao


    companion object {
        @Volatile
        private var INSTANCE: WPMRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WPMRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WPMRoomDatabase::class.java,
                    "Wpm_database"
                ).addCallback(WPMDatabaseCallback(scope)).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

        private class WPMDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wpmDao())
                    }
                }
            }
        }

        fun populateDatabase(wpmDao: WPMDao) {
            wpmDao.deleteAll()

            var wpm: WPM

            val scores = listOf("50", "45", "40", "35", "30", "60")

            for (score in scores) {
                wpm = WPM(0, score)
                wpmDao.insert(wpm)
            }

        }
    }

}
