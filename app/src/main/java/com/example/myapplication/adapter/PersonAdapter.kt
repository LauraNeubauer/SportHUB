package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PersonApi.model.Person
import com.example.myapplication.databinding.StrangerProfilFragmentBinding

class PersonAdapter(
    private val dataset: List<Person>
) : RecyclerView.Adapter<PersonAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: StrangerProfilFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = StrangerProfilFragmentBinding.inflate(
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

        holder.binding.tvName.text = item.name.first
    }
}