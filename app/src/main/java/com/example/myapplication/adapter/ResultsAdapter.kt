package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
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
        holder.binding.tvTime.text = randomTime()
        holder.binding.tvGroupOneGoals.text = randomGoals()
        holder.binding.tvGroupTwoGoals.text = randomGoals()
        holder.binding.grouponepic.setImageResource(randomPicture())
        holder.binding.grouptwopic.setImageResource(randomPicture())
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun randomTime() : String {
        val randomTime = listOf<String>("11", "17", "19", "22", "31", "42", "45+2", "48", "52", "57", "63", "67", "71", "75", "88", "90+2", "90+4")
        return randomTime.random()
    }

    fun randomGoals() : String {
        val randomGoals = listOf<String>("0", "0", "0", "0", "1", "1", "1", "2", "3", "4")
        return randomGoals.random()
    }

    fun randomPicture() : Int {
        var pictures = listOf<Int>(R.drawable.achievement_1, R.drawable.award_1, R.drawable.download, R.drawable.download__1_)
        return pictures.random()
    }

}