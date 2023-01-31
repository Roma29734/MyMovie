package com.example.mymovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.MovieItemRowBinding
import com.example.mymovie.utils.IMAGE_DOP

class MoviePagingAdapter : PagingDataAdapter<Result, MoviePagingAdapter.MyViewHolder>(
    COMPARATOR
) {

    inner class MyViewHolder(val binding: MovieItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    var callBackDel: ((city: Result) -> Unit)? = null

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val moviePositions = getItem(position)
        holder.binding.apply {
            if (moviePositions != null) {
                textNameMovie.text = moviePositions.title
                imageMove.load("$IMAGE_DOP${moviePositions.poster_path}")
            }
            item.setOnClickListener {

                callBackDel?.let { it1 -> moviePositions?.let { it2 -> it1(it2) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MovieItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object COMPARATOR : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

}