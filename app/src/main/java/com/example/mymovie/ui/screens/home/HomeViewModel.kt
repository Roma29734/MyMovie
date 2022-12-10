package com.example.mymovie.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.ParentModel
import com.example.mymovie.domain.MovieUserCase
import com.example.mymovie.utils.LoadState
import com.example.mymovie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
): ViewModel() {

    private var _movieState = MutableStateFlow(HomeState())
    val movieState get() = _movieState

    private val list = mutableListOf<ParentModel>()

    fun getMovie() {
        try {
            movieState.update { it.copy(loadState = LoadState.LOADING) }
            viewModelScope.launch {
                movieUserCase.getPopularMovie().collect { result ->

                    when(result) {
                        is Resource.Success -> {
                            val model = result.data?.let { it1 -> ParentModel("Популярные", it1.results ) }
                            movieState.update { it.copy(successPopular = model) }
                            model?.let { it1 -> list.add(it1) }
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            movieState.update { it.copy(loadState = LoadState.ERROR) }
                            return@collect
                        }
                    }
                }
                movieUserCase.getUpcomingMovieCase().collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            val model = result.data?.let { it1 -> ParentModel("Предстояшие", it1.results ) }
                            movieState.update { it.copy(successUpcoming = model) }
                            model?.let { it1 -> list.add(it1) }
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            movieState.update { it.copy(loadState = LoadState.ERROR) }
                            return@collect
                        }
                    }

                }
                movieUserCase.getTopRatedMovieCase().collect { result ->


                    when(result) {
                        is Resource.Success -> {
                            val model = result.data?.let { it1 -> ParentModel("Топовый рейтинг", it1.results ) }
                            movieState.update { it.copy(successTopRated = model) }
                            model?.let { it1 -> list.add(it1) }
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            movieState.update { it.copy(loadState = LoadState.ERROR) }
                            return@collect
                        }
                    }
                }
                movieUserCase.getNowPlayingCase().collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            val model = result.data?.let { it1 -> ParentModel("Играют сейчас", it1.results ) }
                            movieState.update { it.copy(successNow = model) }
                            model?.let { it1 -> list.add(it1) }
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            movieState.update { it.copy(loadState = LoadState.ERROR) }
                        }
                    }
                }

                movieState.update { it.copy(loadState = LoadState.SUCCESS) }
                Log.d("ameba","${list.size}")
            }
        }catch (e: Exception) {
            movieState.update { it.copy(loadState = LoadState.ERROR) }
        }
    }
}