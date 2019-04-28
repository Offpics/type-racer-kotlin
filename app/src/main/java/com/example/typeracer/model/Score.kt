package com.example.typeracer.model

import com.google.firebase.firestore.IgnoreExtraProperties


/**
 * Score POJO.
 */
@IgnoreExtraProperties
data class Score(
    var wpm: String? = null
) {
    companion object {

        const val FIELD_WPM = "wpm"
    }
}