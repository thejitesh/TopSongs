package com.encora.topsongs.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.encora.topsongs.network.model.Song

@Entity(tableName = "song_table")
data class TopSongTableRow(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String?
)

fun TopSongTableRow.convertToNetworkModel(): Song {
    return Song(title = title)
}