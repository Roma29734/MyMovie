package com.example.mymovie.ui.screens.viewPager.popular

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.firebase.Resours
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import com.example.mymovie.domain.MovieUserCase
import com.example.mymovie.domain.userCase.GetPopularMovieCase
import com.example.mymovie.ui.screens.home.HomeState
import com.example.mymovie.utils.InternetConnection
import com.example.mymovie.utils.LoadState
import com.example.mymovie.utils.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
): ViewModel() {

    private var _movieState = MutableStateFlow(HomeState())
    val movieState get() = _movieState

    fun getPopularMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.getPopularMovie().collect {result ->
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

