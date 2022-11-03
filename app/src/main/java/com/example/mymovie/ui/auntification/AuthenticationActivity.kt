package com.example.mymovie.ui.auntification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mymovie.R
import com.example.mymovie.databinding.ActivityAutificationBinding
import com.example.mymovie.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_autification.*
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAutificationBinding
    private lateinit var navController: NavController
    private  var firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.MyTolsBar)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.reg_host) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(reg_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MainActivity::class.java)
        if(firebaseAuth.currentUser != null) {
            startActivity(intent)
        }
    }

    fun goToMain() {
        val intentuser = Intent(this, MainActivity::class.java)
        startActivity(intentuser)
        finish()
    }
}