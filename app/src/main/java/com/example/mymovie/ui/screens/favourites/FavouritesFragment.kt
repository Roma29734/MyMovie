package com.example.mymovie.ui.screens.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding

    private lateinit var adapter: FavouritesAdapter

    private lateinit var favouritesViewModel: FavouritesViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel = ViewModelProvider(this)[FavouritesViewModel::class.java]
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)

//        adapter, recycler
        adapter = FavouritesAdapter()
        recyclerView = binding.favouritesRecycler
//        recyclerView.adapter = adapter
        return binding.root
    }
}