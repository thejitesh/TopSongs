package com.encora.topsongs.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.encora.topsongs.R
import com.encora.topsongs.viewmodels.TopSongViewModel
import com.encora.topsongs.viewmodels.TopSongViewModelFactory
import kotlinx.android.synthetic.main.fragment_top_songs_list.*

class TopSongsListFragment : Fragment(R.layout.fragment_top_songs_list) {

    private var adapter: TopSongsListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        populateTopSongsList()

        val factory = TopSongViewModelFactory()
        val mainViewModel = ViewModelProviders.of(this, factory)[TopSongViewModel::class.java]

        mainViewModel.fetTopSongs()

        mainViewModel.getTopSongs().observe(viewLifecycleOwner, {
            adapter?.setData(it)
        })
    }

    private fun populateTopSongsList() {
        adapter = TopSongsListAdapter()
        fragment_top_songs_list_rv.layoutManager = LinearLayoutManager(requireContext())
        fragment_top_songs_list_rv.adapter = adapter
        setUpDecorationForList()
    }

    private fun setUpDecorationForList() {
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_verticle_space)
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable)
        }
        fragment_top_songs_list_rv.addItemDecoration(dividerItemDecoration)
    }
}