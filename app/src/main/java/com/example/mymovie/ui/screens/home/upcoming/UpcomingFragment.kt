package com.example.mymovie.ui.screens.home.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentUpcomingBinding
import com.example.mymovie.ui.adapter.MoviePagingAdapter
import com.example.mymovie.ui.screens.detail.DetailFragmentDirections
import com.example.mymovie.ui.screens.home.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class UpcomingFragment :
    BaseFragment<FragmentUpcomingBinding>
        (FragmentUpcomingBinding::inflate) {

    private val viewModel: SharedViewModel by activityViewModels()
    private val adapter = MoviePagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.callBackNav = {
            val action = DetailFragmentDirections.actionDetailFragmentSelf(it)
            Navigation.findNavController(view).navigate(action)
        }

        binding.recycler.adapter = adapter

        lifecycleScope.launch (Dispatchers.IO) {
            viewModel.getUpcomingMovie().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}