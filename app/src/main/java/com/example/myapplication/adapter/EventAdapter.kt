package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.EventsHomeListItemBinding
import com.example.myapplication.model.Event

class EventAdapter(
    private val dataset: List<Event>,
) : RecyclerView.Adapter<EventAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: EventsHomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Verschoben vor der return-Anweisung
        val binding =
            EventsHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        Log.d("TAG", "Adapter hat datenset gezogen")

        holder.binding.btClubname.text = item.club
        holder.binding.btParticipants.text = item.participants.toString()
        holder.binding.tvLevel.text = item.level
        holder.binding.tvPlace.text = item.place
        holder.binding.tvTime.text = item.time
        holder.binding.tvPlayer.text = item.player
        Log.d("TAG", "Binding Elemente positiv")
        if (item.participants == 1) {
            holder.binding.ivProfil4.setImageResource(item.pictureOne)
        } else if (item.participants == 2) {
            holder.binding.ivProfil3.setImageResource(item.pictureTwo)
        } else if (item.participants == 3) {
            holder.binding.ivProfil2.setImageResource(item.pictureThree)
        } else if (item.participants == 4) {
            holder.binding.ivProfil1.setImageResource(item.pictureFour)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}