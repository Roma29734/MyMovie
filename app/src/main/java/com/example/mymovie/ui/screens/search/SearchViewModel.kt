package com.example.mymovie.ui.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import com.example.mymovie.domain.MovieUserCase
import com.example.mymovie.ui.screens.home.HomeState
import com.example.mymovie.utils.LoadState
import com.example.mymovie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
) : ViewModel() {

    private var _movieSearchResult = MutableStateFlow(HomeState())
    val movieSearchResult get() = _movieSearchResult

    fun searchMovie(query: String) {
        val search = "%$query%"
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.searchMovieCase(search).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        _movieSearchResult.update { it.copy(loadState = LoadState.LOADING) }
                    }

                    is Resource.Error -> {
                        _movieSearchResult.update { it.copy(loadState = LoadState.ERROR) }
                    }

                    is Resource.Success -> {
                        _movieSearchResult.update { it.copy(loadState = LoadState.SUCCESS, successState = result.data) }
                    }
                }
            }
        }
    }
}