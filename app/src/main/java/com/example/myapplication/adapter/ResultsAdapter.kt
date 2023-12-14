package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ResultsListItemSmallBinding
import com.example.myapplication.model.Results

class ResultsAdapter(
    private val dataset: List<Results>
) : RecyclerView.Adapter<ResultsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ResultsListItemSmallBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultsAdapter.ItemViewHolder {
        val binding = ResultsListItemSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvGroupOne.text = item.mainPlayer
        holder.binding.tvGroupTwo.text = item.playerOpps
        holder.binding.tvLand.text = item.place

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}