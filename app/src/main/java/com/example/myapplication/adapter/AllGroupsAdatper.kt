package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AllChatsItemBinding
import com.example.myapplication.model.Chat
import com.example.myapplication.viewmodel.FirebaseViewModel

// Der Adapter der die ChatGruppen übergibt

class AllGroupsAdatper(
    private val dataset: List<Chat>,
    private val firebaseVM: FirebaseViewModel,
) : RecyclerView.Adapter<AllGroupsAdatper.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: AllChatsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllGroupsAdatper.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Verschoben vor der return-Anweisung
        val binding =
            AllChatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]


        holder.binding.btAdd.setOnClickListener {
            firebaseVM.addChatGroupToCollection(item.groupName, item.groupPic)
        }

        holder.binding.tvNameChat.text = item.groupName
        holder.binding.tvLastMessage.text = "Ohne Beitrittsanfrage"
        holder.binding.ivGroupPic.setImageResource(item.groupPic)
    }
}