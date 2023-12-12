package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FinderResultsMatchPersonBinding
import com.example.myapplication.model.Request

class FinderResultAdapter(
    private val dataset: List<Request>
) : RecyclerView.Adapter<FinderResultAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: FinderResultsMatchPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = FinderResultsMatchPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.btClub.text = item.person.club
        holder.binding.tvWonStridng.text = item.person.age
        holder.binding.tvName.text = (item.person.firstName + " " + item.person.lastName)

    }
}