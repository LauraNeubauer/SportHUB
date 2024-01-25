package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FinderResultsMatchPersonBinding
import com.example.myapplication.model.Club

class ClubAdapter(
    private val dataset: List<Club>,
    private val viewmodel: ViewModel
) : RecyclerView.Adapter<ClubAdapter.ItemViewHolder>() {

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
        //est, pokale, ligen, quote, tuniere

        holder.binding.btClub.text = item.sport
        holder.binding.tvName.text = item.name

        holder.binding.textStatAge.text = "EST."
        holder.binding.tvStatAge.text = item.est.toString()
        holder.binding.textStatMatches.text = "POKALE"
        holder.binding.tvStatMatches.text = item.pokale.toString()
        holder.binding.textStatPokale.text = "LIGEN"
        holder.binding.tvStatPokale.text = item.ligen.toString()
        holder.binding.textStatSize.text = "QUOTE"
        holder.binding.tvStatSize.text = item.Quote
        holder.binding.textStatWins.text = "TUNIERE"
        holder.binding.tvStatWins.text = item.tuniere.toString()
        holder.binding.tvEntfernung.text = item.entfernung.toString() + " km entfernt"
        holder.binding.tvSport.text = item.sport.toString()
        holder.binding.tvLevel2.text = ""
        holder.binding.tvDate.text = ""
        val maxCharCount = 97
        val originalText = item.bio
        val truncatedText = if (originalText.length > maxCharCount) {
            originalText.substring(0, maxCharCount - 3) + "..."
        } else {
            originalText
        }

        holder.binding.tvTitle.text = truncatedText
        holder.binding.btProfile.setOnClickListener {
            viewmodel.setCurrentClub(item)
            // Navigation zu einem anderen Fragment mit Hilfe des NavController
            holder.itemView.findNavController().navigate(R.id.strangerClubFragment)
        }
    }
}