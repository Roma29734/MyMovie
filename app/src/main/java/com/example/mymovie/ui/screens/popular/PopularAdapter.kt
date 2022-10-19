package com.example.mymovie.ui.screens.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovie.R

import com.example.mymovie.data.remote.model.Result
import com.example.mymovie.databinding.CardRowBinding
import com.example.mymovie.ui.screens.home.HomeFragmentDirections
import com.example.mymovie.utils.IMAGE_DOP

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {

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
            .load("$IMAGE_DOP${positionMovieList.poster_path}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imageMovie)

        holder.binding.rowCard.setOnClickListener {
            val action = HomeFragmentDirections.actionRootFragmentToDetailFragment(positionMovieList)
            holder.itemView.findNavController().navigate(action)
        }
    }



    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(list: List<Result>) {
        movieList = list
        notifyDataSetChanged()
    }
}