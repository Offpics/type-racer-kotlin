package com.example.typeracer.data

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread


class WPMRepository(private val wpmDao: WPMDao) {

    val allWpms: LiveData<List<WPM>> = wpmDao.getAllWpms()

    @WorkerThread
    suspend fun insert(wpm: WPM) {
        wpmDao.insert(wpm)
    }
}