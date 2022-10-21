package com.example.mymovie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.model.Result

@Database(entities = [Result::class], version = 1)
abstract class MovieDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{

        @Volatile
        private var INSTANSE: MovieDataBase? = null

        fun getDataBase(context: Context): MovieDataBase {
            val tempInstance = INSTANSE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instanse = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    "movie_table"
                ).build()
                INSTANSE = instanse
                return instanse
            }

        }

    }
}