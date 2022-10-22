package com.example.mymovie.di

import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.data.remote.firebase.AuthenticationRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class fireBaseModule {

    @Provides
    fun providesFireBaseAuth() :FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository = impl

}