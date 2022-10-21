package com.example.mymovie.ui.screens.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovie.R
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.CardVerticalRowBinding
import com.example.mymovie.ui.screens.home.HomeFragmentDirections
import com.example.mymovie.utils.IMAGE_DOP

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.MyViewHolder>() {

    private var favouritesMovieList = emptyList<Result>()

    inner class MyViewHolder(val binding: CardVerticalRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardVerticalRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionFavouritesList = favouritesMovieList[position]

        holder.binding.textCardTitle.text = positionFavouritesList.title
        holder.binding.textCardDate.text = positionFavouritesList.release_date

        Glide.with(holder.itemView.context)
            .load("$IMAGE_DOP${positionFavouritesList.poster_path}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imageMovie)

        holder.binding.rowCard.setOnClickListener {
            val action = FavouritesFragmentDirections.actionFavouritesToDetailFragment(positionFavouritesList)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return favouritesMovieList.size
    }

    fun setData(list: List<Result>) {
        favouritesMovieList = list
        notifyDataSetChanged()
    }
}