package com.example.mymovie.ui


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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
//    private var _binding: ActivityMainBinding? = null
    private lateinit var binding: ActivityMainBinding

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

        binding.bottobNavigation.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(nav_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}