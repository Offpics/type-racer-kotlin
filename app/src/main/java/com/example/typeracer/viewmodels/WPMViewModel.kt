package com.example.typeracer.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.typeracer.data.WPM
import com.example.typeracer.data.WPMDatabase
import com.example.typeracer.data.WPMRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext


class WPMViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: WPMRepository
    val allWpms: LiveData<List<WPM>>

    init {
        val wpmDao = WPMDatabase.getDatabase(application).wpmDao()
        repository = WPMRepository(wpmDao)
        allWpms = repository.allWpms
    }

    fun insert(wpm: WPM) = scope.launch(Dispatchers.IO) {
        repository.insert(wpm)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}