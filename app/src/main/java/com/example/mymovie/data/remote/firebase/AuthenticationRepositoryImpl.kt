package com.example.mymovie.data.remote.firebase

import com.example.mymovie.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepositoryImpl: AuthenticationRepository {
    private val auth = FirebaseAuth.getInstance()


    override suspend fun createUser(email: String, password: String): Resours<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Resours.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resours.Failure(e)
        }
    }

    override suspend fun singUser(email: String, password: String): Resours<FirebaseUser> {

        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Resours.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resours.Failure(e)
        }
    }

    override suspend fun logOut() {
        auth.signOut()
    }
}

