package com.example.mymovie.ui.screens.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentFavouritesBinding
import com.example.mymovie.ui.MainActivity
import com.example.mymovie.ui.adapter.MainAdapter
import com.example.mymovie.ui.auntification.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {

    private val viewModel: FavouritesViewModel by viewModels()
    private var adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.callBackDel = { result ->
            val action = FavouritesFragmentDirections.actionFavouritesToDetailFragment(result)
            view.let { Navigation.findNavController(it).navigate(action) }
        }

        binding.include2.textView.text = "Избранное"
        binding.favouritesRecycler.adapter = adapter
        binding.favouritesRecycler.layoutManager = GridLayoutManager(context, 3)

        viewModel.readFavouritesMovie.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }
    }
}