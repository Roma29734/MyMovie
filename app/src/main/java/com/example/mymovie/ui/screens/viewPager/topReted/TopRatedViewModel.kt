package com.example.mymovie.ui.screens.viewPager.topReted

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TopRatedViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RemoteRepository = RemoteRepository()

    val topRatedList: MutableLiveData<Response<movieModel>> = MutableLiveData()

    fun getTopRateMovieList() {
        viewModelScope.launch {
            topRatedList.value = repository.getTopRatedMovie()
        }
    }
}