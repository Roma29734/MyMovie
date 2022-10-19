package com.example.mymovie.ui.auntification.service

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class AuthenticationRepository {
    private val auth = FirebaseAuth.getInstance()

    fun createUser(email: String, password: String, context: Context) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(context, "okey", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun singuser(email: String, pasword: String, context: Context) {
        auth.signInWithEmailAndPassword(email, pasword).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(context, "okey", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun logOut() {
        auth.signOut()
    }
}