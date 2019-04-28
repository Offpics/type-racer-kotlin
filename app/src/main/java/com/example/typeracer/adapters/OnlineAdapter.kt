package com.example.typeracer.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.typeracer.R
import com.example.typeracer.model.Score
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.recyclerview_item.view.*


/**
 * RecyclerView adapter for a list of Restaurants.
 */
open class OnlineAdapter(query: Query) :
    FirestoreAdapter<OnlineAdapter.ViewHolder>(query) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            snapshot: DocumentSnapshot
        ) {

            val score = snapshot.toObject(Score::class.java)
            if (score == null) {
                return
            }

            itemView.textView5.text = score.wpm


        }
    }
}


