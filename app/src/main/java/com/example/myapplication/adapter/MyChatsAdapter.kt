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

// Adapter-Klasse für die Anzeige der eigenen Chat-Liste
class MyChatsAdapter(
    private val dataset: MutableList<Chat>, // Liste von Chat-Objekten als Datensatz für den Adapter
    private val firebaseVM: FirebaseViewModel  // Instanz des FirebaseViewModels für Dateninteraktion
) : RecyclerView.Adapter<MyChatsAdapter.ItemViewHolder>() {

    // View Holder-Klasse, die die Darstellung eines einzelnen Items in der RecyclerView hält
    inner class ItemViewHolder(val binding: ChatListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Methode zur Erstellung eines neuen View Holders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChatsAdapter.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Verschoben vor der return-Anweisung
        val binding =
            ChatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // Methode zur Abrufung der Anzahl der Elemente im Datensatz
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Methode zur Anpassung der Inhalte eines View Holders basierend auf seiner Position im Datensatz
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        // Setzen von Texten und Daten für die Anzeige von Chat-Informationen
        holder.binding.tvNameChat.text = item.groupName
        if (item.messages.sortedBy { it.timestamp }.last().text != null) {
            holder.binding.tvLastMessage.text = item.messages.sortedBy { it.timestamp }.last().text
        } else {
            "Willkommen"
        }
        holder.binding.tvChatLastTexter.text = (item.messages.sortedBy { it.timestamp }.last()?.from?.split(" ")?.firstOrNull() ?: "") + ":"

        // Click-Listener für das Chat-Item zum Setzen des aktuellen Chats und Navigieren zu dem jeweiligen Chat
        holder.binding.cvChat.setOnClickListener {
            firebaseVM.setCurrentChat(item)
            holder.itemView.findNavController().navigate(R.id.chatDetailFragment)
        }
        holder.binding.tvChatDay.text = "Heute"
    }
}