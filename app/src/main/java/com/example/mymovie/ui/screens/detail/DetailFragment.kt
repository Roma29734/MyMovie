package com.example.mymovie.ui.screens.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentDetailBinding
import com.example.mymovie.ui.adapter.MoviePagingAdapter
import com.example.mymovie.utils.IMAGE_DOP
import com.example.mymovie.utils.LoadState
import com.example.mymovie.utils.SaveShared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModels()
    private var valueBool: Boolean? = null
    private val recommendationsAdapter = MoviePagingAdapter()
    private val similarAdapter = MoviePagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        valueBool = SaveShared.getFavorite(context, args.moviewModel.id.toString())
        if (valueBool == true) binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_fav_yes)
        else binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_fav_non)

        recommendationsAdapter.callBackDel = {
            val action = DetailFragmentDirections.actionDetailFragmentSelf(it)
            Navigation.findNavController(view).navigate(action)
        }
        similarAdapter.callBackDel = {
            val action = DetailFragmentDirections.actionDetailFragmentSelf(it)
            Navigation.findNavController(view).navigate(action)
        }

        binding.apply {
            recRecyclerView.adapter = recommendationsAdapter
            similarRecyclerView.adapter = similarAdapter
            context?.let {
                imageView.load("$IMAGE_DOP${args.moviewModel.poster_path}")
            }
            textTitle.text = args.moviewModel.title
            textData.text = args.moviewModel.release_date
            textSubTitle.text = args.moviewModel.overview

            toolBar.imageButtonBack.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }

            toolBar.imageButtonFav.setOnClickListener {
                if(viewModel.user == null) {
                    Toast.makeText(context, "Нужно пройти регистрацию", Toast.LENGTH_SHORT).show()
                } else {
                    clickFavButton()
                }
            }
        }

        viewModel.getRecommendMovie(args.moviewModel.id)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieResult.collectLatest { uiState ->
                    when(uiState.loadState) {
                        LoadState.LOADING -> {
                        }
                        LoadState.ERROR -> {
                            Toast.makeText(context, "произашла ошибка", Toast.LENGTH_SHORT).show()
                        }
                        LoadState.SUCCESS -> {
                            uiState.successRec?.let { recommendationsAdapter.submitData(lifecycle, it) }
                            uiState.successSimilar?.let { similarAdapter.submitData(lifecycle, it) }
                        }
                    }
                }
            }
        }
    }

    private fun clickFavButton() {
        valueBool = if (valueBool == true) {
            viewModel.deleteFavourites(args.moviewModel)
            binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_fav_non)
            SaveShared.setFavorite(context, args.moviewModel.id.toString(), false)
            SaveShared.getFavorite(context, args.moviewModel.id.toString())
        } else {
            viewModel.addFavourites(args.moviewModel)
            binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_fav_yes)
            SaveShared.setFavorite(context, args.moviewModel.id.toString(), true)
            SaveShared.getFavorite(context, args.moviewModel.id.toString())
        }
    }
}