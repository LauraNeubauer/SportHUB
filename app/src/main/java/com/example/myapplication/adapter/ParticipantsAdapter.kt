package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.databinding.ParticipantsListItemBinding
import com.example.myapplication.model.Chat

class ParticipantsAdapter(
    private val dataset: List<Chat>,
    private val firebaseVM: FirebaseViewModel,
    private val personViewModel: ViewModel
) : RecyclerView.Adapter<ParticipantsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ParticipantsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParticipantsAdapter.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Verschoben vor der return-Anweisung
        val binding =
            ParticipantsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

    }
}