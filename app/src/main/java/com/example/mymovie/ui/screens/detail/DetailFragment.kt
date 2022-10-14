package com.example.mymovie.ui.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mymovie.R
import com.example.mymovie.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding = _binding!!

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        _binding = FragmentDetailBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}