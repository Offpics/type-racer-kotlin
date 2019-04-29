package com.example.typeracer

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.typeracer.adapters.OnlineAdapter
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_online.*

class OnlineActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var query: Query

    lateinit var adapter: OnlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online)

        firestore = FirebaseFirestore.getInstance()

        query = firestore.collection("scores").orderBy("wpm", Query.Direction.ASCENDING).limit(LIMIT.toLong())

        adapter = object: OnlineAdapter(query) {}

        recyclerOnline.layoutManager = LinearLayoutManager(this)
        recyclerOnline.adapter = adapter


    }

    override fun onStart() {
        super.onStart()

        // Start sign in if necessary
//        if (shouldStartSignIn()) {
//            startSignIn()
//            return
//        }

        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (FirebaseAuth.getInstance().currentUser != null) {
            menuInflater.inflate(R.menu.signedin_menu, menu)
        } else {
            menuInflater.inflate(R.menu.signedout_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_signout -> {
                AuthUI.getInstance().signOut(this).addOnCompleteListener{
                    invalidateOptionsMenu()
                    Toast.makeText(this, "Succesfully signed out!", Toast.LENGTH_LONG).show()
                }
//                startSignIn()
//                recreate()
//                invalidateOptionsMenu()
            }
            R.id.action_signin -> {
                startSignIn()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode != Activity.RESULT_OK) {
                if (response == null) {
                    // User pressed the back button.
                    finish()
                } else if (response.error != null && response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
//                    showSignInErrorDialog(R.string.message_no_network)
                } else {
//                    showSignInErrorDialog(R.string.message_unknown)
                }
            }
            invalidateOptionsMenu()
            Toast.makeText(this, "Succesfully signed in!", Toast.LENGTH_LONG).show()
        }
    }

    private fun shouldStartSignIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser == null
    }

    private fun startSignIn() {
        // Sign in with Firebase UI.
        val intent = AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(listOf(AuthUI.IdpConfig.EmailBuilder().build()))
            .setIsSmartLockEnabled(false)
            .build()

        startActivityForResult(intent, RC_SIGN_IN)
    }


    companion object {

        private const val RC_SIGN_IN = 9001

        private const val LIMIT = 50
    }
}
