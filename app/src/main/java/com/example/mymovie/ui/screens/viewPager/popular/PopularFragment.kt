package com.example.mymovie.ui.screens.viewPager.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding

    private lateinit var adapter: PopularAdapter

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        popularViewModel = ViewModelProvider(this)[PopularViewModel::class.java]
        binding = FragmentPopularBinding.inflate(inflater, container, false)

        adapter = PopularAdapter()
        recyclerView = binding.mainRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularViewModel.getPopularMovieList()
        popularViewModel.movieList.observe(viewLifecycleOwner) { list ->
            list?.body()?.let { adapter.setData(it.results) }
        }
    }
}