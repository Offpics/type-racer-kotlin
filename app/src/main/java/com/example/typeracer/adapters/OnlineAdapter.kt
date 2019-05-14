package com.example.typeracer.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.typeracer.R
import com.example.typeracer.model.Score
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.leaderboardactivity_item.view.textView5
import kotlinx.android.synthetic.main.onlineactivity_item.view.*


/**
 * RecyclerView adapter for a list of Restaurants.
 */
open class OnlineAdapter(query: Query) :
    FirestoreAdapter<OnlineAdapter.ViewHolder>(query) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.onlineactivity_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), position)
        Log.d("OnlineAdapter", position.toString())
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


        fun bind(snapshot: DocumentSnapshot, position: Int) {

            val score = snapshot.toObject(Score::class.java) ?: return

            itemView.textView5.text = score.wpm
            itemView.textView6.text = score.email
            itemView.textView7.text = (position + 1).toString()


        }
    }
}


