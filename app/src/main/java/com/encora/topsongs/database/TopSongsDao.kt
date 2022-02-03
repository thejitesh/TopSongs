package com.encora.topsongs.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopSongsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(note: TopSongTableRow)


    @Query("SELECT * FROM song_table")
    fun getAllTopSongs(): List<TopSongTableRow>?
}