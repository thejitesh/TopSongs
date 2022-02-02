package com.encora.topsongs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.encora.topsongs.viewmodels.TopSongViewModel
import com.encora.topsongs.viewmodels.TopSongViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = TopSongViewModelFactory()
        val mainViewModel = ViewModelProviders.of(this, factory)[TopSongViewModel::class.java]

        mainViewModel.fetTopSongs()

        mainViewModel.getTopSongs().observe(this, {

        })
    }
}