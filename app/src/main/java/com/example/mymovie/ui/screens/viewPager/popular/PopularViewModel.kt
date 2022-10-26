package com.example.mymovie.ui.screens.viewPager.popular

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.data.remote.firebase.Resours
import com.example.mymovie.data.remote.retrofit.repository.RemoteRepository
import com.example.mymovie.utils.InternetConnection
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: RemoteRepository,
    @ApplicationContext private val context: Context,
): ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: MutableLiveData<State> = _state

    val movieList: MutableLiveData<Response<movieModel>> = MutableLiveData()

    fun getPopularMovieList() {
        _state.value = State.Loading
        val conect = InternetConnection(context)
        viewModelScope.launch {
            movieList.value = repository.getPopularMovie()
        }
    }


}
sealed class State{
    object Loading : State()
    class NoInternet(val message : String) : State()
    object Success : State()
}

