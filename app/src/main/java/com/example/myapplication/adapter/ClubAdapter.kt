package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ClubListItemBinding
import com.example.myapplication.model.Club

class ClubAdapter(
    private val dataset: List<Club>,
) : RecyclerView.Adapter<ClubAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ClubListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubAdapter.ItemViewHolder {
        val binding =
            ClubListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ClubAdapter.ItemViewHolder, position: Int) {
        var item = dataset[position]

        holder.binding.btParticipants.text = item.participants.toString()
    }
}