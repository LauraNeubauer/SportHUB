package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PersonApi.ChatViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.PersonApi.model.ChatData
import com.example.myapplication.databinding.ChatListItemBinding

class ChatAdapter(
    private val dataset: List<ChatData>,
    private val chatVM : ChatViewModel,
    private val personVM: PersonViewModel,
) : RecyclerView.Adapter<ChatAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ChatListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Verschoben vor der return-Anweisung
        val binding =
            ChatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

    }
}