package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.GameListItemBinding
import com.example.myapplication.model.Results

class ResultsAdapter(
    private val dataset: List<Results>
) : RecyclerView.Adapter<ResultsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: GameListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultsAdapter.ItemViewHolder {
        val binding = GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvDate.text = item.date
        holder.binding.shots.text = item.shots
        holder.binding.tvLvl.text = item.mainLvl
        holder.binding.tvLvlOpps.text = item.oppsLvl
        holder.binding.tvNameMy.text = item.mainPlayer
        holder.binding.tvNameOpp.text = item.playerOpps

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}