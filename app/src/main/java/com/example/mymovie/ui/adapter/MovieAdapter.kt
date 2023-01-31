package com.example.mymovie.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.MovieItemRowBinding
import com.example.mymovie.utils.IMAGE_DOP

class MovieAdapter(
) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: MovieItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    var callBackDel: ((city: Result) -> Unit)? = null

    private var movie = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MovieItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val moviePositions = movie[position]

        Log.d("checkBagAdapter","Установил данные movieAdapter")

        holder.binding.textNameMovie.text = moviePositions.title

        holder.binding.imageMove.load("$IMAGE_DOP${moviePositions.poster_path}")

        holder.binding.item.setOnClickListener {

            callBackDel?.let { it1 -> it1(moviePositions) }
        }
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    fun setMovie(List: List<Result>) {
        Log.d("checkBagAdapter","Пришли данные в movieAdapter")
        movie = List
        notifyDataSetChanged()
    }
}