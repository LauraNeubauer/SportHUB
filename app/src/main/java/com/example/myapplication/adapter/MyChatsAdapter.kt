package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ChatListItemBinding
import com.example.myapplication.model.Chat
import com.example.myapplication.viewmodel.FirebaseViewModel

class MyChatsAdapter(
    private val dataset: MutableList<Chat>,
    private val firebaseVM: FirebaseViewModel
) : RecyclerView.Adapter<MyChatsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ChatListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChatsAdapter.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Verschoben vor der return-Anweisung
        val binding =
            ChatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvNameChat.text = item.groupName
        if (item.messages.sortedBy { it.timestamp }.last().text != null) {
            holder.binding.tvLastMessage.text = item.messages.sortedBy { it.timestamp }.last().text
        } else {
            "Willkommen"
        }
        holder.binding.tvChatLastTexter.text = (item.messages.sortedBy { it.timestamp }.last()?.from?.split(" ")?.firstOrNull() ?: "") + ":"
        holder.binding.cvChat.setOnClickListener {
            firebaseVM.setCurrentChat(item)
            holder.itemView.findNavController().navigate(R.id.chatDetailFragment)
        }
        holder.binding.tvChatDay.text = "Heute"
    }
}