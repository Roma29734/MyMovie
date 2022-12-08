package com.example.mymovie.ui.screens.viewPager.topReted

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
): ViewModel() {

    private var _movieState = MutableStateFlow(HomeState())
    val movieState get() = _movieState

    fun getTopRatedMovieCase() {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.getTopRatedMovieCase().collect {result ->
                when(result) {
                    is Resource.Error -> {
                        _movieState.update { it.copy(loadState = LoadState.ERROR) }
                    }
                    is Resource.Success -> {
                        _movieState.update { it.copy(loadState = LoadState.SUCCESS, successState = result.data) }
                    }
                    is Resource.Loading -> {
                        _movieState.update { it.copy(loadState = LoadState.LOADING) }
                    }
                }
            }
        }
    }
}