package com.example.mymovie.data.remote.firebase

import android.util.Log
import com.example.mymovie.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthenticationRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun createUser(email: String, password: String): Resours<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Resours.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resours.Failure(e)
        }
    }

    override suspend fun singUser(email: String, password: String): Resours<FirebaseUser> {

        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resours.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("authVIewModel","$e")
            Resours.Failure(e)
        }
    }

    override suspend fun logOut() {
        firebaseAuth.signOut()
    }
}

