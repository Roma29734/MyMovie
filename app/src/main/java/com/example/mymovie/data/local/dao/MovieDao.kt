package com.example.mymovie.data.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymovie.data.remote.model.Result
import retrofit2.http.DELETE

interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieItemModel: Result)

    @DELETE
    suspend fun deleteMovie(movieItemModel: Result)

    @Query("SELECT * FROM movie_table")
    fun getAllMovie(): LiveData<List<Result>>
}