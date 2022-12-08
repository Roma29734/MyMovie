package com.example.mymovie.domain.userCase

import androidx.lifecycle.LiveData
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result
import javax.inject.Inject

class ReadAllLocalMovieCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): LiveData<List<Result>> = repository.readAllMovie
}