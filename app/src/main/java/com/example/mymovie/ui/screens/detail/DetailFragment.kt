package com.example.mymovie.ui.screens.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentDetailBinding
import com.example.mymovie.utils.IMAGE_DOP
import com.example.mymovie.utils.SaveShared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModels()
    private var valueBool: Boolean? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        valueBool = SaveShared.getFavorite(context, args.moviewModel.id.toString())
        context?.let {
            binding.imageView.load("$IMAGE_DOP${args.moviewModel.poster_path}")
        }
        binding.textTitle.text = args.moviewModel.title
        binding.textData.text = args.moviewModel.release_date
        binding.textSubTitle.text = args.moviewModel.overview

        if (valueBool == true) binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_star_yes_state)
        else binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_star_non_state)

        binding.toolBar.imageButtonBack.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.toolBar.imageButtonFav.setOnClickListener {
            if(viewModel.user == null) {
                Toast.makeText(context, "Нужно пройти регистрацию", Toast.LENGTH_SHORT).show()
            } else {
                clickFavButton()
            }
        }
    }

    private fun clickFavButton() {
        valueBool = if (valueBool == true) {
            viewModel.deleteFavourites(args.moviewModel)
            binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_star_non_state)
            SaveShared.setFavorite(context, args.moviewModel.id.toString(), false)
            SaveShared.getFavorite(context, args.moviewModel.id.toString())
        } else {
            viewModel.addFavourites(args.moviewModel)
            binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_star_yes_state)
            SaveShared.setFavorite(context, args.moviewModel.id.toString(), true)
            SaveShared.getFavorite(context, args.moviewModel.id.toString())
        }
    }
}