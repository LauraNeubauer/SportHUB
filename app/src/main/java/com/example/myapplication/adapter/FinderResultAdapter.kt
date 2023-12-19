package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.R
import com.example.myapplication.databinding.FinderResultsMatchPersonBinding

class FinderResultAdapter(
    private val dataset: List<PersonData>,
    private val viewModel: PersonViewModel,
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

        holder.binding.btClub.text = "Club"

        holder.binding.tvName.text = item.name

        holder.binding.textStatAge.text = "ALTER"
        holder.binding.tvStatAge.text = item.age
        holder.binding.textStatMatches.text = "MATCHES"
        holder.binding.tvStatMatches.text = item.matches
        holder.binding.textStatPokale.text = "POKALE"
        holder.binding.tvStatPokale.text = item.trophys
        holder.binding.textStatSize.text = "GRÖSSE"
        holder.binding.tvStatSize.text = item.size
        holder.binding.textStatWins.text = "WINS"
        holder.binding.tvStatWins.text = item.wins

        holder.binding.btProfile.setOnClickListener {
            viewModel.setCurrentProfile(item)
            // Navigation zu einem anderen Fragment mit Hilfe des NavController
            holder.itemView.findNavController().navigate(R.id.strangerProfilFragment)
        }
    }
}