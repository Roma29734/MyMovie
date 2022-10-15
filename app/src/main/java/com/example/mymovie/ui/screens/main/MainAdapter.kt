package com.example.mymovie.ui.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovie.R

import com.example.mymovie.data.remote.model.Result
import com.example.mymovie.databinding.CardRowBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var movieList = emptyList<Result>()

    inner class MyViewHolder(val binding: CardRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionMovieList = movieList[position]

        holder.binding.textCardTitle.text = positionMovieList.title
        holder.binding.textCardDate.text = positionMovieList.release_date
        Glide.with(holder.itemView.context)
            .load(positionMovieList.poster_path)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imageMovie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(list: List<Result>) {
        movieList = list
        notifyDataSetChanged()
    }
}