package com.encora.topsongs.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.encora.topsongs.network.model.AudioUrl
import com.encora.topsongs.network.model.Image
import com.encora.topsongs.network.model.Song

@Entity(tableName = "song_table")
data class TopSongTableRow(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val audioLink: String? = null,
)

fun TopSongTableRow.convertToNetworkModel(): Song {
    return Song(
        title = title,
        imageList = mutableListOf(Image(url = imageUrl ?: "")),
        audioUrlList = mutableListOf(AudioUrl(url = audioLink ?: ""))
    )
}