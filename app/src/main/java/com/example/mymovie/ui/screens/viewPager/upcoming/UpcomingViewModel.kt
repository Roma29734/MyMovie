package com.example.mymovie.ui.screens.viewPager.upcoming

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UpcomingViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RemoteRepository = RemoteRepository()

    val upcomingList: MutableLiveData<Response<movieModel>> = MutableLiveData()

    fun getUpcomingMovieList() {
        viewModelScope.launch {
            upcomingList.value = repository.getUpcomingMovie()
        }
    }
}