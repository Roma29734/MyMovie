package com.example.mymovie.domain.userCase

import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import com.example.mymovie.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUpcomingMovieCase @Inject constructor(private val repository: RemoteRepository) {
    operator fun invoke (): Flow<Resource<movieModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getUpcomingMovie(1)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(com.example.mymovie.utils.Resource.Error("ошибка"))
        }
    }
}