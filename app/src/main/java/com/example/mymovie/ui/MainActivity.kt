package com.example.mymovie.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mymovie.R
import com.example.mymovie.databinding.ActivityMainBinding
import com.example.mymovie.ui.auntification.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController

        binding.bottobNavigation.setupWithNavController(navController)

    }

    fun goToRegister() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
    }

    fun restartAct() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}


