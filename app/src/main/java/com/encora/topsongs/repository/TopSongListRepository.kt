package com.encora.topsongs.repository

import com.encora.topsongs.network.api.APIClientBuilder
import com.encora.topsongs.network.model.Feed

class TopSongListRepository {

    suspend fun fetTopSongs(): Feed {
        //decide whether to fetch data from server or database
        val feed = APIClientBuilder.buildAPI().getTopSongs(20)
        return feed;
    }
}