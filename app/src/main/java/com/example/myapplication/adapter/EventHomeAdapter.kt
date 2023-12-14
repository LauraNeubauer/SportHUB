package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.EventsHomeListItemBinding
import com.example.myapplication.databinding.MatchHomeListItemBinding
import com.example.myapplication.model.Event

class EventHomeAdapter(private val dataset: List<Event>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (dataset[position].Match == true) {
            2
        } else {
            1
        }
    }

    inner class EventsItemViewHolder(val binding: EventsHomeListItemBinding) : RecyclerView.ViewHolder(binding.root)

    inner class MatchItemViewHolder(val binding: MatchHomeListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Moved before the return statement
        return when (viewType) {
            1 -> {
                val binding = EventsHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EventsItemViewHolder(binding)
            }
            2 -> {
                val binding = MatchHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MatchItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataset[position]
        Log.d("TAG", "Adapter hat datenset gezogen")

        when (holder) {
            is EventsItemViewHolder -> {
                // Handle EventsItemViewHolder
                holder.binding.btClubname.text = item.club
                holder.binding.btParticipants.text = item.participants.toString()
                holder.binding.tvLevel.text = item.level
                holder.binding.tvPlace.text = item.place
                holder.binding.tvTime.text = item.time
                holder.binding.tvPlayer.text = item.player
                Log.d("TAG", "Binding Elemente positiv")
                // Handle image resources for EventsItemViewHolder
            }
            is MatchItemViewHolder -> {
                holder.binding.btClubname.text = item.club
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
