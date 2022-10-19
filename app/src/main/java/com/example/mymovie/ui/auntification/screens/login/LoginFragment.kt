package com.example.mymovie.ui.auntification.screens.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentLoginBinding
import com.example.mymovie.ui.MainActivity
import com.example.mymovie.ui.auntification.AuthenticationActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textGoToRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.matButtonGo.setOnClickListener {
            if(inputCheck(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())) {
                loginViewModel.singUser(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())
                binding.textViewDalaee.text = "Дальше"

                binding.textViewDalaee.setOnClickListener {
                    val inten = (requireActivity() as AuthenticationActivity).goToMain()
                    inten
                }
            } else {
                Toast.makeText(context, "Поля пусты", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck (title: String, subTitle: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(subTitle))
    }
}