package com.encora.topsongs.network.api

import com.encora.topsongs.network.model.Feed
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {

    @GET("/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit={limit}/xml")
    suspend fun getTopSongs(@Path("limit") limit: Int = 20) : Feed
}