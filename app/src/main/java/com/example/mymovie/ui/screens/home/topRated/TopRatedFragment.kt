package com.example.mymovie.ui.screens.home.topRated

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentTopRatedBinding
import com.example.mymovie.ui.adapter.MoviePagingAdapter
import com.example.mymovie.ui.screens.home.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedFragment :
    BaseFragment<FragmentTopRatedBinding>
        (FragmentTopRatedBinding::inflate) {

    private val viewModel: SharedViewModel by activityViewModels()
    private val adapter = MoviePagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(context, 3)
        lifecycleScope.launch {
            viewModel.getTopRatedFlow().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}