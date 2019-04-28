package com.example.typeracer

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class OnlineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online)

    }

    override fun onStart() {
        super.onStart()

        // Start sign in if necessary
        if (shouldStartSignIn()) {
            startSignIn()
            return
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.online_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_signout -> {
                AuthUI.getInstance().signOut(this)
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
    }
}
