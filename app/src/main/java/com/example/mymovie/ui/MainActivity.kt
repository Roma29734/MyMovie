package com.example.mymovie.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mymovie.R
import com.example.mymovie.databinding.ActivityMainBinding
import com.example.mymovie.ui.auntification.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
//    private var _binding: ActivityMainBinding? = null
    private lateinit var binding: ActivityMainBinding
    private  var firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.Mytoolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
//         Make sure actions in the ActionBar get propagated to the NavController
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.profile,
            R.id.search,
            R.id.home
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
//
        binding.bottobNavigation.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(nav_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun goToRegister() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        if(firebaseAuth.currentUser == null) {
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        goToRegister()
    }
}