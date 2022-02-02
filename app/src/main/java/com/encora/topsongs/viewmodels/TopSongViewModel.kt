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

class TopSongViewModel : ViewModel() {

    //DI can be used to inject
    private val repository = TopSongListRepository()
    private val topSongs = MutableLiveData<List<Song>>()

    fun fetTopSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val feed = repository.fetTopSongs()
                Log.d("", "")
            } catch (e: Exception) {
                Log.d("", "")
            }
        }
    }

    fun getTopSongs(): LiveData<List<Song>> {
        return topSongs
    }
}