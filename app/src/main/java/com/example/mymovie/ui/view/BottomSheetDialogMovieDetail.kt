package com.example.mymovie.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.example.mymovie.R
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.BottomSheetMovieDetailBinding
import com.example.mymovie.utils.IMAGE_DOP
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogMovieDetail constructor(
    private val info: Result,
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetMovieDetailBinding? = null
    private val binding get() = _binding!!

    var callBackNav: ((city: Result) -> Unit)? = null

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
            imageView.load("$IMAGE_DOP${info.poster_path}") {
                placeholder(R.drawable.ic_arrow_back_non_palka)
                listener(onStart = {
                    Log.d("imageBugBottomSheet","onStart")
                }, onError = { _, img ->
                    Log.d("imageBugBottomSheet","onError ${img.throwable.message}")
                }, onCancel = {
                    Log.d("imageBugBottomSheet","onCancel")
                }, onSuccess = {_, _ ->
                    Log.d("imageBugBottomSheet","onSuccess")
                })
            }
            textNameMovie.text = info.title
            textDescriptoin.text = info.overview
            materialButtonMore.setOnClickListener {
                Toast.makeText(context, "нави гей шен", Toast.LENGTH_SHORT).show()
                callBackNav?.let { it1 -> it1(info) }
                this@BottomSheetDialogMovieDetail.dismiss()
            }
        }
    }
}