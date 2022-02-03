package com.encora.topsongs.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.encora.topsongs.R

class TopSongsDetailsFragment : Fragment(R.layout.fragment_song_details) {

    val args: TopSongsDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val xx = args.selectedSong
    }
}