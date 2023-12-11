package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ChatListItemBinding
import com.example.myapplication.model.Chat

class ChatAdapter(
    private val dataset: List<Chat>,
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
        var item = dataset[position]

        holder.binding.ivGroupPic.setImageResource(item.groupPic)
        holder.binding.tvChatDay.text = item.lastMessageDay
        holder.binding.tvNameChat.text = item.groupName
        holder.binding.tvChatLastTexter.text = item.lastMessageFrom
        holder.binding.tvLastMessage.text = item.lastMessage
    }
}