package com.example.mymovie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mymovie.data.model.Result
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository

class GetRecommendationsPaging (
    private val repository: RemoteRepository,
    private val id: Int
): PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1

        return try {
            val responses = repository.getRecommendations(id = id)
            toLoadResult(responses, page)
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun toLoadResult(data: movieModel, position: Int): LoadResult<Int, Result> {
        return LoadResult.Page(
            data = data.results,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (data.results.isEmpty()) null else position + 1
        )
    }
}