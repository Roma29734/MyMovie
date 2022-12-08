package com.example.mymovie.ui.screens.viewPager.topReted

import android.graphics.Color
import android.net.Network
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.FragmentTopRatedBinding
import com.example.mymovie.ui.adapter.MainAdapter
import com.example.mymovie.ui.screens.home.HomeFragmentDirections
import com.example.mymovie.utils.InternetConnection
import com.example.mymovie.utils.LoadState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedFragment : BaseFragment<FragmentTopRatedBinding>(FragmentTopRatedBinding::inflate) {

    private val viewModel: TopRatedViewModel by viewModels()
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topRateRecycler.layoutManager = GridLayoutManager(context, 3)
        binding.topRateRecycler.adapter = adapter

        adapter.callBackDel = { result ->
            val action = HomeFragmentDirections.actionRootFragmentToDetailFragment(result)
            view.let { Navigation.findNavController(it).navigate(action) }
        }

        viewModel.getTopRatedMovieCase()
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