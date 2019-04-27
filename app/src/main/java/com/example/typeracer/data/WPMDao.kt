package com.example.typeracer.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface WPMDao {

    @Query("SELECT * from wpm_table ORDER BY wpm ASC")
    fun getAllWpms(): LiveData<List<WPM>>

    @Insert
    fun insert(wpm: WPM)

    @Query("DELETE FROM wpm_table")
    fun deleteAll()
}