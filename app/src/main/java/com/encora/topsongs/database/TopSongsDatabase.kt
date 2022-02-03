package com.encora.topsongs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TopSongTableRow::class], version = 1, exportSchema = false)
abstract class TopSongsDatabase : RoomDatabase() {

    abstract fun getTopSongsDao(): TopSongsDao

    companion object {
        private const val DB_NAME = "top_songs_database.db"

        @Volatile
        private var instance: TopSongsDatabase? = null
        private val LOCK = Any()


        fun getInstance(context: Context): TopSongsDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
            return instance;
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TopSongsDatabase::class.java,
            DB_NAME
        ).build()
    }
}
