package com.encora.topsongs.repository

import com.encora.topsongs.database.TopSongsDatabase
import com.encora.topsongs.database.convertToNetworkModel
import com.encora.topsongs.network.api.APIClientBuilder
import com.encora.topsongs.network.model.Song
import com.encora.topsongs.network.model.convertToDatabaseTableRow

class TopSongListRepository {

    suspend fun fetTopSongs(database: TopSongsDatabase?): List<Song>? {
        var topSongsList = mutableListOf<Song>()
        val songsDao = database?.getTopSongsDao()
        val songsListFromDatabase = songsDao?.getAllTopSongs()
        if (songsListFromDatabase.isNullOrEmpty()) {
            val topSongsFeedData = APIClientBuilder.buildAPI().getTopSongs(20)
            if (topSongsFeedData?.topSongsList != null) {
                for (topSong in topSongsFeedData.topSongsList) {
                    songsDao?.insertSong(topSong.convertToDatabaseTableRow())
                }
                topSongsList = topSongsFeedData.topSongsList
            }
        } else {
            for (topSongTableRow in songsListFromDatabase) {
                topSongsList.add(topSongTableRow.convertToNetworkModel())
            }
        }
        return topSongsList;
    }
}