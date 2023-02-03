package com.example.mymovie.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.BottomSheetMovieDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogMovieDetail constructor(
    private val info: Result,
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetMovieDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            imageView.load(info.poster_path)
            textNameMovie.text = info.title
            textDescriptoin.text = info.overview
            materialButtonMore.setOnClickListener {
                Toast.makeText(context, "нави гей шен", Toast.LENGTH_SHORT).show()
            }
        }
    }
}