package com.example.mymovie.ui.screens.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mymovie.data.model.Result
import com.example.mymovie.data.paging.PagingRepository
import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.domain.MovieUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
    authenticationRepository: AuthenticationRepository,
    private val pagingRepository: PagingRepository
): ViewModel() {

    val user = authenticationRepository.currentUser

    private var _movieResult = MutableStateFlow(DetailState())
    val movieResult get() = _movieResult

    fun addFavourites(movieModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.insertLocalMovieCase(movieModel)
        }
    }
    fun deleteFavourites(movieModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.deleteLocalMovieCase(movieModel)
        }
    }

    fun getRecommendMovie(id: Int) {
        viewModelScope.launch (Dispatchers.IO) {
            pagingRepository.getRecommendations(id)
                .cachedIn(viewModelScope)
                .collect { result ->
                _movieResult.update { it.copy(successRec = result) }
            }
            pagingRepository.getSimilarMovie(id).collect { result ->
                _movieResult.update { it.copy(successSimilar = result) }
            }
        }
    }
}