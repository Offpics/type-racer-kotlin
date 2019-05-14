package com.example.typeracer

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NavUtils
import android.widget.TextView
import com.example.typeracer.data.WPM
import com.example.typeracer.viewmodels.WPMViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class GameOverActivity : AppCompatActivity() {

    private lateinit var wpmViewModel: WPMViewModel
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val displayMessage = "WPM: $message"

        val textView: TextView = findViewById(R.id.textview_wpm)
        textView.text = displayMessage

        // Insert score into the local database
        wpmViewModel = ViewModelProviders.of(this).get(WPMViewModel::class.java)
        val score = WPM(message)
        wpmViewModel.insert(score)


        // Insert score into the online database
        if (isUserSignedIn()) {
            // Firestore
            firestore = FirebaseFirestore.getInstance()


            // Create a new score
            val newScore = HashMap<String, Any>()
            newScore["wpm"] = message
            newScore["email"] = FirebaseAuth.getInstance().currentUser?.email.toString()


            // Add a new document with a generated ID
            firestore.collection("scores").add(newScore)

        }
    }

    /** On back button pressed go to ParentActivity, which is MainActivity. */
    override fun onBackPressed() {
        NavUtils.navigateUpFromSameTask(this)
    }

    private fun isUserSignedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

}
