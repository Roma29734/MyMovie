package com.example.mymovie.di

import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class apiBaseModule {

    @Provides
    fun provideRemoteRepository(): RemoteRepository = RemoteRepository()
}