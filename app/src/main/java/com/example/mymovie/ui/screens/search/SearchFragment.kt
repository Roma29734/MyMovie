package com.example.mymovie.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentSearchBinding
import com.example.mymovie.utils.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()
    private val adapter = SearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerSearch.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerSearch.adapter = adapter
        
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieSearchResult.collectLatest { uiState ->
                    when(uiState.loadState) {
                        LoadState.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        LoadState.ERROR -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            Toast.makeText(context, "произашла ошибка", Toast.LENGTH_SHORT).show()
                        }
                        LoadState.SUCCESS -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            uiState.successState?.let { adapter.setData(it.results) }
                        }
                    }
                }
            }
        }


        binding.include3.SearchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    if(p0.isNotEmpty()) {
                        p0.let { viewModel.searchMovie(p0) }
                    }
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    if(p0.isNotEmpty()) {
                        p0.let { viewModel.searchMovie(p0) }
                    }
                }
                return false
            }
        })
    }
}