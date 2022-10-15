package com.example.mymovie.ui.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

//    private val args by navArgs<>()
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        context?.let {
            Glide.with(it)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${args.moviewModel.poster_path}")
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
        }
        binding.textTitle.text = args.moviewModel.title
        binding.textData.text = args.moviewModel.release_date
        binding.textSubTitle.text = args.moviewModel.overview

        return binding.root
    }

}