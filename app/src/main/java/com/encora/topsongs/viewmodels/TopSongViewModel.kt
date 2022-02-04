package com.encora.topsongs.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.encora.topsongs.database.TopSongsDatabase
import com.encora.topsongs.network.model.Song
import com.encora.topsongs.repository.TopSongListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TopSongViewModel @Inject constructor() : ViewModel() {

    private val topSongsData = MutableLiveData<List<Song>>()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        Log.d("Something went wrong: ", exception?.message ?: "")
    }

    fun fetTopSongs(database: TopSongsDatabase?, repository: TopSongListRepository) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val topSongs = repository.fetTopSongs(database)
            withContext(Dispatchers.Main) {
                topSongsData.value = topSongs!!
            }
        }
    }

    fun getTopSongs(): LiveData<List<Song>> {
        return topSongsData
    }
}