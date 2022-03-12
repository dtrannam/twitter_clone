package com.codepath.apps.restclienttemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.TweetsAdapter.*
import com.codepath.apps.restclienttemplate.models.Tweet


class TweetsAdapter (val tweets: ArrayList<Tweet>): RecyclerView.Adapter <ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        val tvUserName = itemView.findViewById<TextView>(R.id.tvUsername)
        val tvTweetBody = itemView.findViewById<TextView>(R.id.tvTweetBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Item Layout
        val view = inflater.inflate(R.layout.item_tweet, parent, false)

        return ViewHolder(view)
    }

    // Clean all elements of the recycler
// Clean all elements of the recycler
    fun clear() {
        tweets.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(tweetList: List<Tweet>) {
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }

    // Populating Data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get data
        val tweet: Tweet = tweets.get(position)
        // Set item based on view an data model
        holder.tvUserName.text = tweet.user?.name
        holder.tvTweetBody.text = tweet.body
        Glide.with(holder.itemView).load(tweet.user?.publicImageUrl).into(holder.ivProfile)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }
}