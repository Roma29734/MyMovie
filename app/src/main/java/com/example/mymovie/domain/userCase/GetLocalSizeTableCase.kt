package com.example.mymovie.domain.userCase

import com.example.mymovie.data.local.repository.MovieRepository
import javax.inject.Inject

class GetLocalSizeTableCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getSizeTable()
}