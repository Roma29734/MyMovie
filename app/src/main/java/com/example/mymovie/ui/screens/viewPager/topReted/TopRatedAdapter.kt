package com.example.mymovie.ui.screens.viewPager.topReted

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

class TopRatedAdapter: RecyclerView.Adapter<TopRatedAdapter.MyViewHolder>() {

    private var topRatedList = emptyList<Result>()

    inner class MyViewHolder(val binding: CardVerticalRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardVerticalRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionMovieList = topRatedList[position]

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
        return topRatedList.size
    }

    fun setTopRatedList(list: List<Result>) {
        topRatedList = list
        notifyDataSetChanged()
    }
}