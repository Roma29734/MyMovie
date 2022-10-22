package com.example.mymovie.ui.screens.viewPager.topReted

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
class TopRatedViewModel @Inject constructor(
    private val repository: RemoteRepository
): ViewModel() {

    val topRatedList: MutableLiveData<Response<movieModel>> = MutableLiveData()

    fun getTopRateMovieList() {
        viewModelScope.launch {
            topRatedList.value = repository.getTopRatedMovie()
        }
    }
}