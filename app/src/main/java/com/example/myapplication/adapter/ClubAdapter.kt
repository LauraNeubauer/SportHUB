package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FinderResultsMatchPersonBinding
import com.example.myapplication.model.Club
import com.example.myapplication.viewmodel.MainViewModel

// Adapter-Klasse für die Anzeige von Clubs in der Ergebnisliste im Finder
class ClubAdapter(
    private val dataset: List<Club>, // Liste von Club-Objekten als Datensatz für den Adapter
    private val viewmodel: MainViewModel // Instanz des MainViewModels für Dateninteraktion
) : RecyclerView.Adapter<ClubAdapter.ItemViewHolder>() {

    // View Holder-Klasse, die die Darstellung eines einzelnen Elements in der RecyclerView hält
    inner class ItemViewHolder(val binding: FinderResultsMatchPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Methode zur Erstellung eines neuen View Holders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = FinderResultsMatchPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    // Methode zur Abrufung der Anzahl der Elemente im Datensatz
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Methode zur Anpassung der Inhalte eines View Holders basierend auf seiner Position im Datensatz
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        // Setzen von Texten und Daten für die Anzeige von Club-Informationen
        holder.binding.btClub.text = item.sport
        holder.binding.tvName.text = item.name

        holder.binding.textStatAge.text = "EST."
        holder.binding.tvStatAge.text = item.est.toString()
        holder.binding.textStatMatches.text = "POKALE"
        holder.binding.tvStatMatches.text = item.pokale.toString()
        holder.binding.textStatPokale.text = "LIGEN"
        holder.binding.tvStatPokale.text = item.ligen.toString()
        holder.binding.textStatSize.text = "QUOTE"
        holder.binding.tvStatSize.text = item.Quote
        holder.binding.textStatWins.text = "TUNIERE"
        holder.binding.tvStatWins.text = item.tuniere.toString()
        holder.binding.tvEntfernung.text = item.entfernung.toString() + " km entfernt"
        holder.binding.tvSport.text = item.sport.toString()
        holder.binding.tvLevel2.text = ""
        holder.binding.tvDate.text = ""

        // Kürzen der Biografie, wenn sie zu lang ist
        val maxCharCount = 35
        val originalText = item.bio
        val truncatedText = if (originalText.length > maxCharCount) {
            originalText.substring(0, maxCharCount - 3) + "..."
        } else {
            originalText
        }
        holder.binding.tvTitle.text = truncatedText

        // Click-Listener für die Schaltfläche "btProfile" zum Navigieren zu einem anderen Fragment
        holder.binding.btProfile.setOnClickListener {
            viewmodel.setCurrentClub(item)
            // Navigation zu einem anderen Profil mit Hilfe des NavController
            holder.itemView.findNavController().navigate(R.id.strangerClubFragment)
        }
    }
}