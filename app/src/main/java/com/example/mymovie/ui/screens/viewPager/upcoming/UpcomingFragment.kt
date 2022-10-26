package com.example.mymovie.ui.screens.viewPager.upcoming

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentUpcomingBinding
import com.example.mymovie.utils.InternetConnection
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingFragment : Fragment() {
    private lateinit var binding: FragmentUpcomingBinding
    private lateinit var adapter: UpcomingAdapter
    private val viewModel by viewModels<UpcomingViewModel>()
    private lateinit var Network: InternetConnection
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        adapter = UpcomingAdapter()
        binding.upcomingRecycler.adapter = adapter
        binding.upcomingRecycler.layoutManager = GridLayoutManager(context, 3)
        Network = context?.let { InternetConnection(it) }!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Network.observe(viewLifecycleOwner) {connect ->
            if(connect) {
                binding.progressBar.visibility = View.INVISIBLE
                viewModel.getUpcomingMovieList()
                viewModel.upcomingList.observe(viewLifecycleOwner) { list ->
                    list?.body()?.let { adapter.setUpcomingList(it.results) }
                }
            } else {
                binding.progressBar.visibility = View.VISIBLE
                Snackbar.make(view, R.string.no_internet, Snackbar.LENGTH_SHORT)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(Color.WHITE)
                    .show()
            }
        }
    }
}