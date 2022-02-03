package com.encora.topsongs.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.encora.topsongs.R
import com.encora.topsongs.database.TopSongsDatabase
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
        setUpViewModelAndListenForChanges()
    }

    private fun setUpViewModelAndListenForChanges() {
        val database  = TopSongsDatabase.getInstance(requireContext())
       // database?.openHelper?.writableDatabase
        val factory = TopSongViewModelFactory()
        val topSongViewModel = ViewModelProviders.of(this, factory)[TopSongViewModel::class.java]
        topSongViewModel.fetTopSongs(database)
        topSongViewModel.getTopSongs().observe(viewLifecycleOwner, {
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