package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.databinding.IncommingBubbleListItemBinding
import com.example.myapplication.databinding.OutgoingBubbleListItemBinding
import com.example.myapplication.model.Message

class ChatDetailAdapter(
    private val dataset: List<Message>,
    private val viewModel: MainViewModel,
    private val personVM: PersonViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MessageInViewHolder(val binding: IncommingBubbleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MessageOutViewHolder(val binding: OutgoingBubbleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val chatIn = 1
    private val chatOut = 2

    override fun getItemViewType(position: Int): Int {
        val itemChatDetail = dataset[position]

        return if (itemChatDetail.send) {
            chatIn
        } else {
            chatOut
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == chatIn) {
            val binding = IncommingBubbleListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            MessageInViewHolder(binding)
        } else {
            val binding = OutgoingBubbleListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            MessageOutViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemChatDetails = dataset[position]

        val participants = personVM.currentGroup
        var allContacts = personVM.contacts

        val group = personVM.loadGroup(participants)

        if (holder is MessageInViewHolder) {
            holder.binding.tvMessage.text = itemChatDetails.text
            holder.binding.tvTime.text = itemChatDetails.timestamp
            holder.binding.tvSend.text = itemChatDetails.from
        } else if (holder is MessageOutViewHolder) {
            holder.binding.tvMessageOut.text = itemChatDetails.text
            holder.binding.tvTimeOut.text = itemChatDetails.timestamp
            holder.binding.tvSendOut.text = itemChatDetails.from
        }
    }
}