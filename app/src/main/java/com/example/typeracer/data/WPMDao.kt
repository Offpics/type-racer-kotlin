package com.example.typeracer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WPMDao {

    @Query("SELECT * from wpm_table ORDER BY wpm ASC")
    fun getAllWpms(): LiveData<List<WPM>>

    @Insert
    fun insert(wpm: WPM)

    @Query("DELETE FROM wpm_table")
    fun deleteAll()
}