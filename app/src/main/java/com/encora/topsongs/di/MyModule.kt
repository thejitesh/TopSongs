package com.encora.topsongs.di

import android.content.Context
import com.encora.topsongs.database.TopSongsDatabase
import com.encora.topsongs.repository.TopSongListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): TopSongsDatabase {
        return TopSongsDatabase.getInstance(appContext)!!
    }

    @Provides
    fun provideRepository(): TopSongListRepository {
        return TopSongListRepository()
    }
}