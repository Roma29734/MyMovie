package com.example.mymovie.ui.auntification.screens.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentLoginBinding
import com.example.mymovie.ui.auntification.AuthenticationActivity
import com.example.mymovie.data.remote.firebase.Resours


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
        binding.matButtonGo.setOnClickListener {
            loginViewModel.singIn(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())
            loginViewModel.state.observe(viewLifecycleOwner) { state ->
                when(state) {
                    is Resours.Failure -> {
                        Toast.makeText(context, "Возникла ошибка ${state.exception}", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                    Resours.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resours.Success -> {
                        Toast.makeText(context, "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.INVISIBLE
                        (requireActivity() as AuthenticationActivity).goToMain()
                    }
                }
            }
        }

        binding.textGotoRegistr.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }
}