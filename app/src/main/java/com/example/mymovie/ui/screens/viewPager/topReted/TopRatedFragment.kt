package com.example.mymovie.ui.screens.viewPager.topReted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.databinding.FragmentTopRatedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    lateinit var binding: FragmentTopRatedBinding

    lateinit var adapter: TopRatedAdapter

    private val viewModel by viewModels<TopRatedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)

        adapter = TopRatedAdapter()
        binding.topRateRecycler.adapter = adapter
        binding.topRateRecycler.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopRateMovieList()
        viewModel.topRatedList.observe(viewLifecycleOwner) { list ->
            list?.body()?.let {adapter.setTopRatedList(it.results)}
        }
    }
}