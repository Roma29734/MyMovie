package com.example.mymovie.data.local.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymovie.data.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieItemModel: Result)

    @Delete
    suspend fun deleteMovie(movieItemModel: Result)

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovie(): LiveData<List<Result>>
}