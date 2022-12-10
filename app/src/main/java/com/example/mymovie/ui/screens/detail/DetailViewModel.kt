package com.example.mymovie.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result
import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.domain.MovieUserCase
import com.example.mymovie.ui.screens.search.SearchState
import com.example.mymovie.utils.LoadState
import com.example.mymovie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
    private val authenticationRepository: AuthenticationRepository,
): ViewModel() {

    val user = authenticationRepository.currentUser

    private var _movieResult = MutableStateFlow(DetailState())
    val movieResult get() = _movieResult

    fun addFavourites(movieModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.insertLocalMovieCase(movieModel)
        }
    }

    fun getRecommendations(id: Int) {
        viewModelScope.launch {
            movieUserCase.getRecommendationsCase(id).collect {result ->
                when(result) {
                    is Resource.Loading -> {
                        _movieResult.update { it.copy(loadState = LoadState.LOADING) }
                    }

                    is Resource.Error -> {
                        _movieResult.update { it.copy(loadState = LoadState.ERROR) }
                    }

                    is Resource.Success -> {
                        _movieResult.update { it.copy(loadState = LoadState.SUCCESS, successRec = result.data) }
                    }
                }
            }
        }
    }

    fun getSimilar(id: Int) {
        viewModelScope.launch {
            movieUserCase.getSimilarMovieCase(id).collect {result ->
                when(result) {
                    is Resource.Loading -> {
                        _movieResult.update { it.copy(loadState = LoadState.LOADING) }
                    }

                    is Resource.Error -> {
                        _movieResult.update { it.copy(loadState = LoadState.ERROR) }
                    }

                    is Resource.Success -> {
                        _movieResult.update { it.copy(loadState = LoadState.SUCCESS, successSimilar = result.data) }
                    }
                }
            }
        }
    }

    fun deleteFavourites(movieModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.deleteLocalMovieCase(movieModel)
        }
    }
}