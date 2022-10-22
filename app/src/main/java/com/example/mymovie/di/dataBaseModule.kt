package com.example.mymovie.di

import android.content.Context
import androidx.room.Room
import com.example.mymovie.data.local.MovieDataBase
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.local.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class dataBaseModule {

    @Provides
    fun provideMovieRepository(impl: MovieRepositoryImpl): MovieRepository = impl

    @Provides
    fun provideMovieDao(appDataBase: MovieDataBase): MovieDao = appDataBase.movieDao()

    @Provides
    @Singleton
    fun provideMovieDataBase(@ApplicationContext appContext: Context): MovieDataBase =
        Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java,
            "movie_table"
        ).build()
}