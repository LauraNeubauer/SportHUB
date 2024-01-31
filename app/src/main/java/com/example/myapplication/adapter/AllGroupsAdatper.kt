package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AllChatsItemBinding
import com.example.myapplication.model.Chat
import com.example.myapplication.viewmodel.FirebaseViewModel

// Adapter-Klasse, die für die Anzeige aller nicht gespeicherten Chat-Gruppen verwendet wird
class AllGroupsAdatper(
    private val dataset: List<Chat>,
    private val firebaseVM: FirebaseViewModel,
) : RecyclerView.Adapter<AllGroupsAdatper.ItemViewHolder>() {

    // View Holder-Klasse, die die Darstellung eines einzelnen Items in der RecyclerView hält
    inner class ItemViewHolder(val binding: AllChatsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Methode zur Erstellung eines neuen View Holders
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllGroupsAdatper.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Verschoben vor der return-Anweisung
        val binding =
            AllChatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // Methode zur Abrufung der Anzahl der Elemente im Datensatz
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Methode zur Anpassung der Inhalte eines View Holders basierend auf seiner Position im Datensatz
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        // Click-Listener für die Schaltfläche "btAdd", um eine Chat-Gruppe der Favoriten hinzuzufügen
        holder.binding.btBack.setOnClickListener {
            firebaseVM.addChatGroupToCollection(item.groupName, item.groupPic)
        }
        // Setzen von Text und Bild für Gruppenname und Gruppenbild
        holder.binding.tvNameChat.text = item.groupName
        holder.binding.tvLastMessage.text = "Ohne Beitrittsanfrage"
        holder.binding.ivGroupPic.setImageResource(item.groupPic)
    }
}