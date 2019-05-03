package com.example.typeracer.data

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread


class WPMRepository(private val wpmDao: WPMDao) {

    val allWpms: LiveData<List<WPM>> = wpmDao.getAllWpms()

    @WorkerThread
    suspend fun insert(wpm: WPM) {
        wpmDao.insert(wpm)
    }
}