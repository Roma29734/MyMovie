package com.example.mymovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovie.databinding.CardVerticalRowBinding
import com.example.mymovie.utils.IMAGE_DOP
import com.example.mymovie.data.model.Result
import com.example.mymovie.ui.screens.home.HomeFragmentDirections

class MainAdapter() : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var movieList = emptyList<Result>()

    var callBackDel: ((city: Result) -> Unit)? = null

    inner class MyViewHolder(val binding: CardVerticalRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardVerticalRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionMovieList = movieList[position]

        holder.binding.textCardTitle.text = positionMovieList.title
        holder.binding.textCardDate.text = positionMovieList.release_date

        holder.binding.imageMovie.load("$IMAGE_DOP${positionMovieList.poster_path}")

        holder.binding.rowCard.setOnClickListener {
//            val action = HomeFragmentDirections.actionRootFragmentToDetailFragment(positionMovieList)
//            holder.itemView.findNavController().navigate(action)
            callBackDel?.invoke(positionMovieList)
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