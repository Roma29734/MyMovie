package com.example.mymovie.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter = SearchAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container , false)

        setHasOptionsMenu(true)

        binding.recyclerSearch.adapter = adapter
        binding.recyclerSearch.layoutManager = GridLayoutManager(context, 3)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_item, menu)
        val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchMovie(query)
                }
                Log.d("searchFragment", query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchMovie(newText)
                }
                Log.d("searchFragment", newText.toString())
                return true
            }
        })
    }

    fun searchMovie(value: String) {
        viewModel.searchMovie(value)
        viewModel.movieSearchResult.observe(viewLifecycleOwner) {list ->
            list?.body()?.let { adapter.setData(it.results) }
            Log.d("searchFragment", list.toString())
        }
    }
}