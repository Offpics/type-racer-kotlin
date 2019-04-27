package com.example.typeracer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val textView = findViewById<TextView>(R.id.textView4).apply {
            text = message
        }
    }
}
