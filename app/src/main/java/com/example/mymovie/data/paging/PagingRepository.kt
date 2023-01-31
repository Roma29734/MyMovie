package com.example.mymovie.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mymovie.data.model.Result
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepository @Inject constructor(
    private val repository: RemoteRepository
) {

    fun getPopularMovie(): Flow<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            GetPopularMoviePaging(repository)
        }.flow

        return pager
    }

    fun getTopRatedMovie(): Flow<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            GetTopRatedMoviePaging(repository)
        }.flow

        return pager
    }

    fun getUpcomingMovie(): Flow<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            GetUpcomingMoviePaging(repository)
        }.flow

        return pager
    }

    fun getNowPlaying(): Flow<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            GetNowPlayingPaging(repository)
        }.flow

        return pager
    }

    fun searchMovie(query: String): Flow<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            SearchMoviePaging(repository, query)
        }.flow

        return pager
    }

    fun getRecommendations(id: Int): Flow<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            GetRecommendationsPaging(repository, id)
        }.flow

        return pager
    }

    fun getSimilarMovie(id: Int): Flow<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                maxSize = 50,
                prefetchDistance = 5,
                initialLoadSize = 20
            )
        ) {
            GetSimilarMoviePaging(repository, id)
        }.flow

        return pager
    }
}