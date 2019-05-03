package com.example.typeracer

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typeracer.adapters.WPMListAdapter
import com.example.typeracer.viewmodels.WPMViewModel


class LeaderboardActivity : AppCompatActivity() {

    private lateinit var wpmViewModel: WPMViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerview)
        val adapter = WPMListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        wpmViewModel = ViewModelProviders.of(this).get(WPMViewModel::class.java)
        wpmViewModel.allWpms.observe(this, Observer { wpms ->
            wpms?.let { adapter.setWpms(it) }
        })
    }
}
