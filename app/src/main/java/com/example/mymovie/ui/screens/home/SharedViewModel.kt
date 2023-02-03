package com.example.mymovie.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mymovie.data.model.Result
import com.example.mymovie.data.paging.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val pagingRepository: PagingRepository
): ViewModel() {

    fun getTopRatedFlow(): Flow<PagingData<Result>> {
        return pagingRepository.getTopRatedMovie().cachedIn(viewModelScope)
    }

    fun getPopularMovie(): Flow<PagingData<Result>> {
        return pagingRepository.getPopularMovie()
    }

    fun getUpcomingMovie(): Flow<PagingData<Result>> {
        return pagingRepository.getUpcomingMovie()
    }

    fun getNowPlaying(): Flow<PagingData<Result>> {
        return pagingRepository.getNowPlaying()
    }
}