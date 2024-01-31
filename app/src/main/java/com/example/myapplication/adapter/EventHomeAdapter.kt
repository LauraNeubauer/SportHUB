package com.example.myapplication.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.EventsHomeListItemBinding
import com.example.myapplication.databinding.MatchHomeListItemBinding
import com.example.myapplication.model.Event
import com.example.myapplication.viewmodel.MainViewModel

// Adapter-Klasse für die Anzeige von Events auf der Startseite
class EventHomeAdapter(
    private val dataset: List<Event>, // Liste von Event-Objekten als Datensatz für den Adapter
    private val mainViewModel: MainViewModel // // Instanz des MainViewModels für Dateninteraktion
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Methode zur Bestimmung des Typs eines Items basierend auf seiner Position im Datensatz
    override fun getItemViewType(position: Int): Int {
        return if (dataset[position].Match == true) {
            2 // Match-Typ
        } else {
            1 // Event-Typ
        }
    }

    // View Holder-Klasse für Events
    inner class EventsItemViewHolder(val binding: EventsHomeListItemBinding) : RecyclerView.ViewHolder(binding.root)

    // View Holder-Klasse für Matches
    inner class MatchItemViewHolder(val binding: MatchHomeListItemBinding) : RecyclerView.ViewHolder(binding.root)

    // Methode zur Erstellung eines neuen View Holders basierend auf dem Item-Typ
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders") // Moved before the return statement
        return when (viewType) {
            1 -> {
                val binding = EventsHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EventsItemViewHolder(binding)
            }
            2 -> {
                val binding = MatchHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MatchItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // Methode zur Anpassung der Inhalte eines View Holders basierend auf seiner Position und dem Item-Typ
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataset[position]
        Log.d("TAG", "Adapter hat datenset gezogen")

        when (holder) {
            is EventsItemViewHolder -> {
                // Handle EventsItemViewHolder
                holder.binding.btClubname.text = item.club
                holder.binding.btParticipants.text = item.participants.toString()
                holder.binding.tvPlace.text = item.place
                holder.binding.tvDate2.text = item.date
                holder.binding.tvLevel.text = item.time + " Uhr"
                holder.binding.tvName.text = item.name
                holder.binding.tvTitleBig.text = item.name
                holder.binding.tvTitleProfil.text = item.level
                Log.d("TAG", "Binding Elemente positiv")

                // Setzen eines Hintergrundfarbe für den Event-Eintrag beim langen Klicken
                holder.itemView.setOnLongClickListener {
                    val color = Color.parseColor("#D9D9D9")
                    val roundedCornerDrawable = GradientDrawable()
                    val cornerRadius = holder.itemView.resources.displayMetrics.density * 20

                    roundedCornerDrawable.cornerRadius = cornerRadius
                    roundedCornerDrawable.setColor(color)

                    holder.binding.cvEventItem.background = roundedCornerDrawable
                    true
                }
            }
            is MatchItemViewHolder -> {
                holder.binding.btClubname.text = item.club
            }
        }
    }

    // Methode zur Abrufung der Anzahl der Elemente im Datensatz
    override fun getItemCount(): Int {
        return dataset.size
    }
}
