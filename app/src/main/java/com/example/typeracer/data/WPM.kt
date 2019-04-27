package com.example.typeracer.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "wpm_table")
class WPM(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val wpm: String
)
