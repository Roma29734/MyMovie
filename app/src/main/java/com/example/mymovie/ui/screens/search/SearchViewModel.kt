package com.example.mymovie.ui.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RemoteRepository
) : ViewModel() {

    val movieSearchResult: MutableLiveData<Response<movieModel>> = MutableLiveData()

    fun searchMovie(query: String) {
        val search = "%$query%"
        if(query.isEmpty()) return
        viewModelScope.launch {
            movieSearchResult.value = repository.searchMovie(search)
        }
    }
}