package com.example.mymovie.ui.screens.viewPager.upcoming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentUpcomingBinding
import com.example.mymovie.ui.adapter.MainAdapter
import com.example.mymovie.utils.LoadState
import dagger.hilt.android.AndroidEntryPoint
import com.example.mymovie.data.model.Result
import com.example.mymovie.ui.screens.home.HomeFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingFragment : BaseFragment<FragmentUpcomingBinding>(FragmentUpcomingBinding::inflate) {

    private val viewModel: UpcomingViewModel by viewModels()
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upcomingRecycler.layoutManager = GridLayoutManager(context, 3)
        binding.upcomingRecycler.adapter = adapter

        adapter.callBackDel = { result ->
            val action = HomeFragmentDirections.actionRootFragmentToDetailFragment(result)
            view.let { Navigation.findNavController(it).navigate(action) }
        }

        viewModel.getUpcomingMovieCase()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieState.collectLatest { uiState ->
                    when(uiState.loadState) {

                        LoadState.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        LoadState.ERROR -> {
                            binding.progressBar.visibility = View.INVISIBLE
                        }

                        LoadState.SUCCESS -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            uiState.successState?.let { adapter.setData(it.results) }
                        }
                    }
                }
            }
        }
    }
}