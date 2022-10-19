package com.example.mymovie.ui.auntification.screens.registr

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mymovie.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {

    lateinit var binding: FragmentRegistrationBinding
    lateinit var regViewModel: RegistrationVIewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        regViewModel = ViewModelProvider(this)[RegistrationVIewModel::class.java]
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButRegistr.setOnClickListener {
            if(inputCheck(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())){
                regViewModel.registrNewUser(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())
                Navigation.findNavController(view).popBackStack()
            } else {
                Toast.makeText(context, "Поля пусты", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck (title: String, subTitle: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(subTitle))
    }

}