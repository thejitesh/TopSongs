package com.encora.topsongs.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.encora.topsongs.R
import com.encora.topsongs.network.model.Song
import com.encora.topsongs.utils.ImageUtils

class TopSongsListAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<TopSongsListAdapter.TopSongsViewHolder>() {

    private var songsList = mutableListOf<Song>()

    fun setData(list: List<Song>?) {
        if (!list.isNullOrEmpty()) {
            songsList.clear()
            songsList.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = songsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopSongsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_song, parent, false))

    override fun onBindViewHolder(holder: TopSongsViewHolder, position: Int) {
        val topSongItem = songsList[position]
        holder.textView.text = topSongItem.title
        holder.itemView.setOnClickListener {
            listener.onItemClick(topSongItem)
        }
        if (!topSongItem.imageList.isNullOrEmpty()) {
            ImageUtils.loadImage(holder.itemView.context, topSongItem.imageList[0].url, holder.imageView)
        }
    }

    inner class TopSongsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_top_song_name)
        val imageView: ImageView = view.findViewById(R.id.item_top_song_image)
    }
}