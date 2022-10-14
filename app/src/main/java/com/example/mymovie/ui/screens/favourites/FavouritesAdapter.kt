package com.example.mymovie.ui.screens.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.data.remote.model.Result
import com.example.mymovie.databinding.CardRowBinding

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.MyViewHolder>() {

    private var favouritesMovieList = emptyList<Result>()

    inner class MyViewHolder(val binding: CardRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return favouritesMovieList.size
    }

    fun setData(list: List<Result>) {
        favouritesMovieList = list
        notifyDataSetChanged()
    }
}