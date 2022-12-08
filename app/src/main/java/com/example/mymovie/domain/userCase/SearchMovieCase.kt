package com.example.mymovie.domain.userCase

import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import com.example.mymovie.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovieCase @Inject constructor(private val repository: RemoteRepository) {
    suspend operator fun invoke(query: String): Flow<Resource<movieModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.searchMovie(query)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}