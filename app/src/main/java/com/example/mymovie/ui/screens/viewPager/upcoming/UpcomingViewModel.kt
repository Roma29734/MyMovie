package com.example.mymovie.ui.screens.viewPager.upcoming

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
class UpcomingViewModel @Inject constructor(
    private val repository: RemoteRepository
) : ViewModel() {

    val upcomingList: MutableLiveData<Response<movieModel>> = MutableLiveData()

    fun getUpcomingMovieList() {
        viewModelScope.launch {
            upcomingList.value = repository.getUpcomingMovie()
        }
    }
}