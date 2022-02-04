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

//Mapping from Network model to DB model approach :
//https://github.com/android/architecture-components-samples/issues/388#issuecomment-790488708
fun TopSongTableRow.convertToNetworkModel(): Song {
    return Song(
        title = title,
        imageList = mutableListOf(Image(url = imageUrl ?: "")),
        audioUrlList = mutableListOf(AudioUrl(url = audioLink ?: ""))
    )
}