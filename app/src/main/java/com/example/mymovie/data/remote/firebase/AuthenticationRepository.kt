package com.example.mymovie.data.remote.firebase

import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {
    suspend fun createUser(email: String, password: String): Resours<FirebaseUser>

    suspend fun singUser(email: String, password: String): Resours<FirebaseUser>

    suspend fun logOut()

}