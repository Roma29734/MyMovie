package com.example.mymovie.ui.screens.detail

import android.os.Bundle
import android.view.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentDetailBinding
import com.example.mymovie.utils.SaveShared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel>()

    private  var valueBool : Boolean? =  null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        valueBool = SaveShared.getFavorite(context, args.moviewModel.id.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            Glide.with(it)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${args.moviewModel.poster_path}")
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
        }
        binding.textTitle.text = args.moviewModel.title
        binding.textData.text = args.moviewModel.release_date
        binding.textSubTitle.text = args.moviewModel.overview
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_ac_bar_item, menu)
        if(valueBool == true) menu[0].setIcon(R.drawable.ic_star_yes_state)
        else menu[0].setIcon(R.drawable.ic_star_non_state)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.star_icon) {
            if(valueBool == true) {
                viewModel.deleteFavourites(args.moviewModel)
                item.setIcon(R.drawable.ic_star_non_state)
                SaveShared.setFavorite(context, args.moviewModel.id.toString(), false)
            } else {
                viewModel.addFavourites(args.moviewModel)
                item.setIcon(R.drawable.ic_star_yes_state)
                SaveShared.setFavorite(context, args.moviewModel.id.toString(), true)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}