package com.example.mymovie.ui.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.remote.model.movieModel
import com.example.mymovie.data.remote.repository.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel: ViewModel() {
    private var  repository: RemoteRepository = RemoteRepository()

    val  movieList: MutableLiveData<Response<movieModel>> = MutableLiveData()

    fun getMuvieList() {
        viewModelScope.launch {
            movieList.value = repository.getMuvies()
        }
    }
}