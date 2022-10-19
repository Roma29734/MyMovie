package com.example.mymovie.ui.screens.profile

import android.app.AlertDialog
import android.app.Application
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import com.example.mymovie.R
import com.example.mymovie.ui.auntification.AuthenticationActivity
import com.example.mymovie.ui.auntification.service.AuthenticationRepository

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val authenticationRepository = AuthenticationRepository()

    private val context = application

    fun exit() {
        authenticationRepository.logOut()
    }

}