package com.example.typeracer.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.typeracer.R
import com.example.typeracer.data.WPM


class WPMListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WPMListAdapter.WPMViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var wpms = emptyList<WPM>() // Cached copy of words

    inner class WPMViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wpmItemView: TextView = itemView.findViewById(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WPMViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WPMViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WPMViewHolder, position: Int) {
        val current = wpms[position]
        holder.wpmItemView.text = current.wpm
    }

    internal fun setWpms(words: List<WPM>) {
        this.wpms = wpms
        notifyDataSetChanged()
    }

    override fun getItemCount() = wpms.size
}