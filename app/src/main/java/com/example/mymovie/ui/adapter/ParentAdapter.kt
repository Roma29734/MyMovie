package com.example.mymovie.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.data.model.ParentModel
import com.example.mymovie.data.model.Result
import com.example.mymovie.databinding.ParentItemRowBinding

class ParentAdapter: RecyclerView.Adapter<ParentAdapter.MyViewHolder>() {
    inner class MyViewHolder (val binding: ParentItemRowBinding): RecyclerView.ViewHolder(binding.root)

    private var model = emptyList<ParentModel?>()

    var callBackDel: ((city: Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ParentItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionModel = model[position]

        if (positionModel != null) {
            holder.binding.textTitleGroup.text = positionModel.title
        }
        val adapter = MovieAdapter()
        Log.d("checkBagAdapter","Установил movieAdapter в parentADapter")
        if(positionModel != null) {
            adapter.setMovie(positionModel.data)
            adapter.callBackDel = callBackDel
            holder.binding.recyclerMovie.adapter = adapter
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    fun setModelItem(list: List<ParentModel?>) {
        Log.d("checkBagAdapter","Пришли данные в parentAdapter")
        model = list
        notifyDataSetChanged()
    }
}