package com.encora.topsongs.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.encora.topsongs.R
import com.encora.topsongs.database.TopSongsDatabase
import com.encora.topsongs.network.model.Song
import com.encora.topsongs.viewmodels.TopSongViewModel
import com.encora.topsongs.viewmodels.TopSongViewModelFactory

class TopSongsListFragment : Fragment(R.layout.fragment_top_songs_list), OnItemClickListener {

    private var adapter: TopSongsListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        populateTopSongsList()
        setUpViewModelAndListenForChanges()
    }

    private fun setUpViewModelAndListenForChanges() {
        val database = TopSongsDatabase.getInstance(requireContext())
        val factory = TopSongViewModelFactory()
        val topSongViewModel = ViewModelProviders.of(this, factory)[TopSongViewModel::class.java]
        topSongViewModel.fetTopSongs(database)
        topSongViewModel.getTopSongs().observe(viewLifecycleOwner, {
            adapter?.setData(it)
        })
    }

    private fun populateTopSongsList() {
        adapter = TopSongsListAdapter(this)
        val topSongsList =  view?.findViewById<RecyclerView>(R.id.fragment_top_songs_list_rv)
        topSongsList?.layoutManager = LinearLayoutManager(requireContext())
        topSongsList?.adapter = adapter
        setUpDecorationForList()
    }

    private fun setUpDecorationForList() {
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_verticle_space)
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable)
        }
        view?.findViewById<RecyclerView>(R.id.fragment_top_songs_list_rv)?.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClick(item: Song?) {
        val action = TopSongsListFragmentDirections.actionTopSongListFragmentToTopSongDetails().setSelectedSong(item)
        view?.findNavController()?.navigate(action)
    }
}