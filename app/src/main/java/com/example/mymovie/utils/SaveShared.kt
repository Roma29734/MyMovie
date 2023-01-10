package com.example.mymovie.utils

import android.content.Context
import android.preference.PreferenceManager

@Suppress("DEPRECATION")
class SaveShared {
    companion object{
        fun setFavorite(context: Context?, key: String, value: Boolean){
            val setFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            setFavoriteShared.edit().putBoolean(key, value).apply()
        }
        fun getFavorite(context: Context?, key: String): Boolean{
            val getFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            return getFavoriteShared.getBoolean(key, false)
        }

        fun deleteAll(context: Context?) {
            val getFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            getFavoriteShared.edit().clear().apply()
        }
    }
}