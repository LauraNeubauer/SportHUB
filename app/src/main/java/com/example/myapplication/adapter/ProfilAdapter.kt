package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProfilListItemBinding
import com.example.myapplication.model.Person

class ProfilAdapter(
    private val dataset: List<Person>
) : RecyclerView.Adapter<ProfilAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ProfilListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfilAdapter.ItemViewHolder {
        val binding = ProfilListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvfAge.text = item.age
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}