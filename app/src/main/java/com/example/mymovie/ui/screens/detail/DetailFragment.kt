package com.example.mymovie.ui.screens.detail

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mymovie.R
import com.example.mymovie.base.BaseFragment
import com.example.mymovie.databinding.FragmentDetailBinding
import com.example.mymovie.ui.adapter.MovieAdapter
import com.example.mymovie.ui.screens.home.HomeFragmentDirections
import com.example.mymovie.utils.IMAGE_DOP
import com.example.mymovie.utils.LoadState
import com.example.mymovie.utils.SaveShared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.addHeaderLenient

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModels()
    private var valueBool: Boolean? = null
    private val recommendationsAdapter = MovieAdapter()
    private val similarAdapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recRecyclerView.adapter = recommendationsAdapter
        binding.similarRecyclerView.adapter = similarAdapter

        recommendationsAdapter.callBackDel = {
            val action = DetailFragmentDirections.actionDetailFragmentSelf(it)
            Navigation.findNavController(view).navigate(action)
        }

        similarAdapter.callBackDel = {
            val action = DetailFragmentDirections.actionDetailFragmentSelf(it)
            Navigation.findNavController(view).navigate(action)
        }

        valueBool = SaveShared.getFavorite(context, args.moviewModel.id.toString())
        context?.let {
            binding.imageView.load("$IMAGE_DOP${args.moviewModel.poster_path}")
        }
        binding.textTitle.text = args.moviewModel.title
        binding.textData.text = args.moviewModel.release_date
        binding.textSubTitle.text = args.moviewModel.overview

        viewModel.getRecommendations(args.moviewModel.id)
        viewModel.getSimilar(args.moviewModel.id)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieResult.collectLatest { uiState ->
                    when(uiState.loadState) {
                        LoadState.LOADING -> {
//                            binding.progressBar.visibility = View.VISIBLE
                        }
                        LoadState.ERROR -> {
//                            binding.progressBar.visibility = View.INVISIBLE
                            Toast.makeText(context, "произашла ошибка", Toast.LENGTH_SHORT).show()
                        }
                        LoadState.SUCCESS -> {
//                            binding.progressBar.visibility = View.INVISIBLE
                            uiState.successRec?.let { recommendationsAdapter.setMovie(it.results) }
                            uiState.successSimilar?.let { similarAdapter.setMovie(it.results) }
                        }
                    }
                }
            }
        }

        if (valueBool == true) binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_fav_yes)
        else binding.toolBar.imageButtonFav.setImageResource(R.drawable.ic_fav_non)

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