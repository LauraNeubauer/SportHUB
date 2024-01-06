package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.PersonApi.ChatViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.PersonApi.model.ChatData
import com.example.myapplication.R
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
        var item = dataset[position]

        val maxLength = 25
        val originalText = item.lastMessage

        if (originalText.length > maxLength) {
            holder.binding.tvLastMessage.text = originalText.substring(0, maxLength) + "..."
        } else {
            holder.binding.tvLastMessage.text = originalText
        }

        holder.binding.ivGroupPic.load(item.pic)
        holder.binding.tvChatDay.text = item.time
        holder.binding.tvNameChat.text = item.groupname
        holder.binding.tvChatLastTexter.text = item.lastChatter

        holder.binding.cvChat.setOnClickListener {
            chatVM.setCurrentChat(item.id - 1)
            holder.itemView.findNavController().navigate(R.id.chatDetailFragment)
        }
    }
}