package com.encora.topsongs.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.encora.topsongs.R
import com.encora.topsongs.network.model.Song

class TopSongsListAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<TopSongsListAdapter.TopSongsViewHolder>() {

    private var songsList = mutableListOf<Song>()

    inner class TopSongsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView?

        init {
            textView = view.findViewById(R.id.item_top_song_name)
        }
    }

    fun setData(list: List<Song>?) {
        if (!list.isNullOrEmpty()) {
            songsList.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopSongsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_song, parent, false))

    override fun onBindViewHolder(holder: TopSongsViewHolder, position: Int) {
        holder.textView?.text = songsList[position].title
        holder.itemView.setOnClickListener {
            listener.onItemClick(songsList[position])
        }
    }

    override fun getItemCount() = songsList.size
}