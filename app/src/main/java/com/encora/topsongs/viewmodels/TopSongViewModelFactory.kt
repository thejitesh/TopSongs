package com.encora.topsongs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class TopSongViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopSongViewModel::class.java)) {
            return TopSongViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}