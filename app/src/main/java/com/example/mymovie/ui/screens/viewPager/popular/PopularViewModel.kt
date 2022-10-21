package com.example.mymovie.ui.screens.viewPager.popular

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PopularViewModel(application: Application): AndroidViewModel(application) {
    private var repository: RemoteRepository = RemoteRepository()

    val movieList: MutableLiveData<Response<movieModel>> = MutableLiveData()


    private val contex = application

    fun getPopularMovieList() {
        viewModelScope.launch {
            movieList.value = repository.getPopularMovie()
        }
    }
}