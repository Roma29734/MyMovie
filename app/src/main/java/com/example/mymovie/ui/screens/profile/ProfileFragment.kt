package com.example.mymovie.ui.screens.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentProfileBinding
import com.example.mymovie.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    fun setUI() {
        lifecycleScope.launch {
            if(viewModel.user == null) {
                binding.registrNon.root.visibility = View.VISIBLE
                registrNon()
            } else {
                binding.registrYes.root.visibility = View.VISIBLE
                registrYes()
            }
        }
    }

    fun registrNon() {
        binding.registrNon.materialButton.setOnClickListener {
            (requireActivity() as MainActivity).goToRegister()
        }
    }

    fun registrYes() {
        viewModel.getFavMovie()
        lifecycleScope.launch {
            binding.registrYes.crdVTextEmail.text = viewModel.user!!.email
            viewModel.favMovie.observe(viewLifecycleOwner) {
                binding.registrYes.crdVTextFav.text = "$it фильма"
            }
            binding.registrYes.materialButton2.setOnClickListener {
                lifecycleScope.launch {
                    dialogExit()
                }
            }
        }
    }

    fun dialogExit() {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Да") { _, _ ->
            lifecycleScope.launch {
                viewModel.exit()
                viewModel.deleteBase()
            }
            Toast.makeText(context, "Вы успешно вышли", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).restartAct()

        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Выйти?")
        builder.setMessage("Вы уверены что хотите выйти из аккаунта?")
        builder.create().show()
    }
}