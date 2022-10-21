package com.example.mymovie.ui.screens.popular

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.local.MovieDataBase
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PopularViewModel(application: Application): AndroidViewModel(application) {
    private var repository: RemoteRepository = RemoteRepository()

    val  movieList: MutableLiveData<Response<movieModel>> = MutableLiveData()

    private lateinit var movieLocalRepository: MovieRepository

    private val contex = application

    fun getMuvieList() {
        viewModelScope.launch {
            movieList.value = repository.getMuvies()
        }
    }

    fun initMovieLocal() {
        val movieData = MovieDataBase.getDataBase(contex).movieDao()
        movieLocalRepository = MovieRepository(movieData)
    }
}