package com.example.typeracer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.nav_header_main.*

const val EXTRA_MESSAGE = "com.example.typeracer.MESSAGE"


class GameActivity : AppCompatActivity() {

//    Data class for a quote
    data class Quote(
        val text: String
    )

    private val quotes: MutableList<Quote> = mutableListOf(
        Quote(text="Alice"),
        Quote(text="Bob")
    )

    private fun randomizeQuotes() {
        quotes.shuffle()
    }

    lateinit var currentQuote: Quote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        randomizeQuotes()
        currentQuote = quotes[0]
        val TextView2 = findViewById<TextView>(R.id.textView2).apply {
            text = currentQuote.text
        }

        val editText = findViewById<EditText>(R.id.editText)

        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() == currentQuote.text) {
                    sentGameOver()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


    fun sentGameOver() {
        val message = "GameOver Screen"
        val intent = Intent(this, GameOverActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}