package com.example.mymovie.di

import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import com.example.mymovie.domain.MovieUserCase
import com.example.mymovie.domain.userCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class domainBaseModule {

    @Singleton
    @Provides
    fun provideGetPopularMovieCase(repository: RemoteRepository) = GetPopularMovieCase(repository)

    @Singleton
    @Provides
    fun provideGetTopRatedMovieCase(repository: RemoteRepository) = GetTopRatedMovieCase(repository)

    @Singleton
    @Provides
    fun provideGetUpcomingMovieCase(repository: RemoteRepository) = GetUpcomingMovieCase(repository)

    @Singleton
    @Provides
    fun provideSearchMovieCase(repository: RemoteRepository) = SearchMovieCase(repository)

    @Singleton
    @Provides
    fun provideReadLocalDataCase(repository: MovieRepository) = ReadAllLocalMovieCase(repository)

    @Singleton
    @Provides
    fun provideInsertLocalDataCase(repository: MovieRepository) = InsertLocalMovieCase(repository)

    @Singleton
    @Provides
    fun provideDeleteLocalDataCase(repository: MovieRepository) = DeleteLocalMovieCase(repository)

    @Singleton
    @Provides
    fun provideGetLocalSizeTableCase(repository: MovieRepository) = GetLocalSizeTableCase(repository)

    @Singleton
    @Provides
    fun provideDeleteLocalTableCase(repository: MovieRepository) = DeleteLocalTableCase(repository)

    @Provides
    fun provideMovieUserCase(
        getPopularMovieCase: GetPopularMovieCase,
        getTopRatedMovieCase: GetTopRatedMovieCase,
        getUpcomingMovieCase: GetUpcomingMovieCase,
        searchMovieCase: SearchMovieCase,
        readAllLocalMovieCase: ReadAllLocalMovieCase,
        insertLocalMovieCase: InsertLocalMovieCase,
        deleteLocalMovieCase: DeleteLocalMovieCase,
        getLocalSizeTableCase: GetLocalSizeTableCase,
        deleteLocalTableCase: DeleteLocalTableCase,
    ) = MovieUserCase(
        getPopularMovieCase,
        getTopRatedMovieCase,
        getUpcomingMovieCase,
        searchMovieCase,
        readAllLocalMovieCase,
        insertLocalMovieCase,
        deleteLocalMovieCase,
        getLocalSizeTableCase,
        deleteLocalTableCase,
    )

}