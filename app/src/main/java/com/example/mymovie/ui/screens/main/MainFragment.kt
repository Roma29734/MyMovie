package com.example.mymovie.ui.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding = _binding!!

    private lateinit var adapter: MainAdapter

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        _binding = FragmentMainBinding.inflate(inflater, container, false)

//        adapter, recycler
        adapter = MainAdapter()
        recyclerView = binding.mainRecycler
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getMuvieList()
        mainViewModel.movieList.observe(viewLifecycleOwner) { list ->
            list?.body()?.let { adapter.setData(it.results) }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}