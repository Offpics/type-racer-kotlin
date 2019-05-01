package com.example.typeracer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView

const val EXTRA_MESSAGE = "com.example.typeracer.MESSAGE"

private var timeStart = System.currentTimeMillis()

class GameActivity : AppCompatActivity() {


//    Data class for a quote
    data class Quote(
        val text: String
    )

    private val quotes: MutableList<Quote> = mutableListOf(
        Quote(text="This is a test sentence.")
//        Quote(text="Alice"),
//        Quote(text="Bob")
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
        val textView: TextView = findViewById(R.id.textview_quote)
        textView.text = currentQuote.text

        // Split current quote into list of words.
        val splitText = currentQuote.text.split("\\s+".toRegex())
        Log.d("GameActivity", splitText[0])
        var currentIndex = 0

        val editText = findViewById<EditText>(R.id.edittext_game)

        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s.toString() == currentQuote.text) {
//                    sentGameOver()
//                }
                if (s.toString() == (splitText[currentIndex] + " ")) {
                    editText.text.clear()
                    currentIndex = currentIndex.inc()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (splitText.size == currentIndex) {
                    Log.d("afterTextChanged", splitText.size.toString())
                    sentGameOver(splitText.size)
                }
            }
        })

        resetTime()

    }

    /** Reset startTime of the activity */
    private fun resetTime() {
        timeStart = System.currentTimeMillis()
    }

    /** Start new activity with the time the user took to complete the game.*/
    private fun sentGameOver(splitTextSize: Int) {
        Log.d("sentGameOver", splitTextSize.toString())
        val timeEnd = System.currentTimeMillis()
        val seconds = (timeEnd - timeStart)
        Log.d("sentGameOver", seconds.toString())

        val wpm: Long = (splitTextSize * 60000) / seconds
        val message = wpm.toString()

        Log.d("GameActivity", wpm.toString())

        val intent = Intent(this, GameOverActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }



}
