package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.IncommingBubbleListItemBinding
import com.example.myapplication.databinding.OutgoingBubbleListItemBinding
import com.example.myapplication.model.Message
import com.example.myapplication.viewmodel.MainViewModel

// Adapter-Klasse für die Anzeige von Chat-Nachrichten (Bubbles) im der Detailansicht
class ChatDetailAdapter(
    private val dataset: MutableList<Message>, // Liste von Nachrichtenobjekten als Datensatz für den Adapter
    private val personVM: MainViewModel,  // Instanz des MainViewModels für Dateninteraktion
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // View Holder-Klasse für eingehende (empfangene) Nachrichten
    inner class MessageInViewHolder(val binding: IncommingBubbleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // View Holder-Klasse für ausgehende (gesendete) Nachrichten
    inner class MessageOutViewHolder(val binding: OutgoingBubbleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Typen für eingehende und ausgehende Nachrichten
    private val chatIn = 1
    private val chatOut = 2

    // Methode zur Bestimmung des Nachrichtentyps basierend auf der Position im Datensatz und des Booleans
    override fun getItemViewType(position: Int): Int {
        val itemChatDetail = dataset[position]

        return if (itemChatDetail.send == false) {
            chatIn
        } else {
            chatOut
        }
    }

    // Methode zur Erstellung eines neuen View Holders basierend auf dem Nachrichtentyp
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

    // Methode zur Abrufung der Anzahl der Elemente im Datensatz
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Methode zur Anpassung der Inhalte eines View Holders basierend auf seiner Position und dem Nachrichtentyp
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemChatDetails = dataset[position]

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