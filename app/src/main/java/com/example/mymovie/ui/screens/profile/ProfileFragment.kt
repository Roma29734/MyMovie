package com.example.mymovie.ui.screens.profile

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentPopularBinding
import com.example.mymovie.databinding.FragmentProfileBinding
import com.example.mymovie.ui.MainActivity
import com.example.mymovie.ui.auntification.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.System.exit
@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val firebase = FirebaseAuth.getInstance().currentUser

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebase?.let {
            val name = firebase.displayName
            val email = firebase.email
            binding.textView4.text = email
        }

        binding.matButExit.setOnClickListener {
            dialogExit()
        }
    }

    fun dialogExit() {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Yes") { _, _ ->
            lifecycleScope.launch {
                viewModel.exit()
            }
            Toast.makeText(context, "Вы успешно вышли", Toast.LENGTH_SHORT).show()
            val inten = (requireActivity() as MainActivity).goToRegister()
            inten
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Выйти?")
        builder.setMessage("Вы уверены что хотите выйти из аккаунта?")
        builder.create().show()
    }
}