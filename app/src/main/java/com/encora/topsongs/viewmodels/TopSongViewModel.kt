package com.encora.topsongs.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.encora.topsongs.repository.TopSongListRepository
import com.encora.topsongs.network.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopSongViewModel : ViewModel() {

    //DI can be used to inject
    private val repository = TopSongListRepository()
    private val topSongsData = MutableLiveData<List<Song>>()

    fun fetTopSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val feed = repository.fetTopSongs()
                withContext(Dispatchers.Main) {
                    topSongsData.value = feed.songList
                }
            } catch (e: Exception) {
                Log.d("", "" + e)
            }
        }
    }

    fun getTopSongs(): LiveData<List<Song>> {
        return topSongsData
    }
}