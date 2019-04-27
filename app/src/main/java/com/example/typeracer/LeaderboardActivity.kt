package com.example.typeracer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.typeracer.adapters.WPMListAdapter
import com.example.typeracer.viewmodels.WPMViewModel


class LeaderboardActivity : AppCompatActivity() {

    private lateinit var wpmViewModel: WPMViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WPMListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wpmViewModel = ViewModelProviders.of(this).get(WPMViewModel::class.java)
        wpmViewModel.allWpms.observe(this, Observer { wpms ->
            wpms?.let { adapter.setWpms(it) }
        })
    }
}
