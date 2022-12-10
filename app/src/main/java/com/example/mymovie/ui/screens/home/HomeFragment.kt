package com.example.mymovie.ui.screens.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentHomeBinding
import com.example.mymovie.ui.adapter.ParentAdapter
import com.example.mymovie.utils.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter = ParentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include.textView.text = "Movie"

        binding.mainRecycler.adapter = adapter

        adapter.callBackDel = {
            val action = HomeFragmentDirections.actionRootFragmentToDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

        viewModel.getMovie()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieState.collectLatest { uiState ->
                    when(uiState.loadState) {
                        LoadState.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        LoadState.ERROR -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            Toast.makeText(context, "ошибка начальника", Toast.LENGTH_SHORT).show()
                        }
                        LoadState.SUCCESS -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            Log.d("checkBagAdapter","Установил parent adapter item в mainFragment")
                            val resalt = listOf(
                                uiState.successPopular,
                                uiState.successUpcoming,
                                uiState.successTopRated,
                                uiState.successNow,
                            )
                            adapter.setModelItem(resalt)
                        }
                    }
                }
            }
        }
    }
}

