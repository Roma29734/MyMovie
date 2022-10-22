package com.example.mymovie.ui.auntification.screens.registr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import com.example.mymovie.databinding.FragmentRegistrationBinding
import com.example.mymovie.ui.auntification.AuthenticationActivity
import com.example.mymovie.data.remote.firebase.Resours
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    lateinit var binding: FragmentRegistrationBinding
    private val viewModel by viewModels<RegistrationViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         binding.matButRegistr.setOnClickListener {
             viewModel.registration(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())
             viewModel.state.observe(viewLifecycleOwner) { state ->
                when(state) {
                    is Resours.Failure -> {
                        Toast.makeText(context, "Возникла ошибка ${state.exception}", Toast.LENGTH_SHORT).show()
                        binding.progressBar.isInvisible
                    }
                    Resours.Loading -> {
                        binding.progressBar.visibility
                    }
                    is Resours.Success -> {
                        Toast.makeText(context, "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show()
                        binding.progressBar.isInvisible
                        (requireActivity() as AuthenticationActivity).goToMain()
                    }
                }
            }
        }
    }
}