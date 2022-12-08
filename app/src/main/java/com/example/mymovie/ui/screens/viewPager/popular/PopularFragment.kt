package com.example.mymovie.ui.screens.viewPager.popular


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.FragmentPopularBinding
import com.example.mymovie.ui.adapter.MainAdapter
import com.example.mymovie.ui.screens.home.HomeFragmentDirections
import com.example.mymovie.utils.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>(FragmentPopularBinding::inflate) {

    private val viewModel: PopularViewModel by viewModels()
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecycler.adapter = adapter
        binding.mainRecycler.layoutManager = GridLayoutManager(context, 3)

        adapter.callBackDel = { result ->
            val action = HomeFragmentDirections.actionRootFragmentToDetailFragment(result)
            view.let { Navigation.findNavController(it).navigate(action) }
        }

        viewModel.getPopularMovieList()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
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