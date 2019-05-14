package com.example.typeracer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.typeracer.R
import com.example.typeracer.data.WPM


class WPMListAdapter internal constructor(
    context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<WPMListAdapter.WPMViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var wpms = emptyList<WPM>() // Cached copy of words

    inner class WPMViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val wpmPosition: TextView = itemView.findViewById(R.id.textView7)
        val wpmItemView: TextView = itemView.findViewById(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WPMViewHolder {
        val itemView = inflater.inflate(R.layout.leaderboardactivity_item, parent, false)
        return WPMViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WPMViewHolder, position: Int) {
        val current = wpms[position]
        holder.wpmItemView.text = current.wpm
        holder.wpmPosition.text = (position + 1).toString()
    }

    internal fun setWpms(wpms: List<WPM>) {
        this.wpms = wpms
        notifyDataSetChanged()
    }

    override fun getItemCount() = wpms.size
}