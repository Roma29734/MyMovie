package com.example.mymovie.domain.userCase

import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result
import javax.inject.Inject

class DeleteLocalMovieCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movieItemModel: Result) = repository.deleteMovie(movieItemModel)
}