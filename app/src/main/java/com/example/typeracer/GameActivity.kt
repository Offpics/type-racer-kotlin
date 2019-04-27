package com.example.typeracer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

const val EXTRA_MESSAGE = "com.example.typeracer.MESSAGE"


class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    fun sentGameOver(view: View) {
        val message = "GameOver Screen"
        val intent = Intent(this, GameOverActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}
