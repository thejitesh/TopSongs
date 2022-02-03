package com.encora.topsongs.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.encora.topsongs.R
import com.encora.topsongs.utils.ImageUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopSongsDetailsFragment : Fragment(R.layout.fragment_song_details) {

    private val topSongsDetailsFragmentArgs: TopSongsDetailsFragmentArgs by navArgs()
    var mediaPlayer: MediaPlayer? = null
    @Volatile
    var isMediaReadyToPlay: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val selectedSong = topSongsDetailsFragmentArgs.selectedSong

        view.findViewById<TextView>(R.id.fragment_song_details_name).text = selectedSong?.title

        ImageUtils.loadImage(
            requireContext(), selectedSong?.imageList?.first()?.url,
            view.findViewById<ImageView>(R.id.fragment_song_details_image)
        )

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(selectedSong?.audioUrlList?.get(0)?.url)
        }


        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
            Log.d("Something went wrong while prepairing Player ", exception?.message ?: "")
        }

        lifecycleScope.launch(Dispatchers.Default + exceptionHandler) {
            mediaPlayer?.prepare() // might take long! (for buffering, etc)
            isMediaReadyToPlay = true
        }

        view.findViewById<TextView>(R.id.fragment_song_details_play_pause).setOnClickListener {
            if (isMediaReadyToPlay) {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                    view.findViewById<TextView>(R.id.fragment_song_details_play_pause).text = "PLAY"
                } else {
                    mediaPlayer?.start()
                    view.findViewById<TextView>(R.id.fragment_song_details_play_pause).text = "STOP"
                }
            } else {
                Toast.makeText(requireContext(), "Media is not Ready Yet", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        view?.findViewById<TextView>(R.id.fragment_song_details_play_pause)?.text = "PLAY"
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}