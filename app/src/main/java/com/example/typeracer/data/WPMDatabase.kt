package com.example.typeracer.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = [WPM::class], version = 1)
public abstract class WPMDatabase : RoomDatabase() {

    abstract fun wpmDao(): WPMDao


    companion object {
        // Make the Database a singleton to prevent having multiple instances of the database opened at the same time.
        @Volatile
        private var INSTANCE: WPMDatabase? = null

        fun getDatabase(context: Context): WPMDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WPMDatabase::class.java,
                    "Wpm_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}