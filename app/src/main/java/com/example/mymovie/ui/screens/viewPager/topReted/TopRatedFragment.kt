package com.example.mymovie.ui.screens.viewPager.topReted

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentTopRatedBinding
import com.example.mymovie.utils.InternetConnection
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    lateinit var binding: FragmentTopRatedBinding
    lateinit var adapter: TopRatedAdapter
    private val viewModel by viewModels<TopRatedViewModel>()
    private lateinit var Network: InternetConnection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)

        adapter = TopRatedAdapter()
        binding.topRateRecycler.adapter = adapter
        binding.topRateRecycler.layoutManager = GridLayoutManager(context, 3)
        Network = context?.let { InternetConnection(it) }!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Network.observe(viewLifecycleOwner) {connect ->
            if(connect) {
                binding.progressBar.visibility = View.INVISIBLE
                viewModel.getTopRateMovieList()
                viewModel.topRatedList.observe(viewLifecycleOwner) { list ->
                    list?.body()?.let { adapter.setTopRatedList(it.results) }
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