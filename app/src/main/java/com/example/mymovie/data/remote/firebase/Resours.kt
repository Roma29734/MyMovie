package com.example.mymovie.data.remote.firebase

import java.lang.Exception

sealed class Resours<out R> {
    data class Success<out R>(val result: R): Resours<R>()
    data class Failure(val exception: Exception): Resours<Nothing>()
    object Loading: Resours<Nothing>()
}