package com.example.typeracer.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wpm_table")
class WPM(
    @PrimaryKey val wpm: String
//    @PrimaryKey(autoGenerate = true) var id: Int,
//    val wpm: String
)
