package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.R
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.databinding.FinderResultsMatchPersonBinding
import com.example.myapplication.viewmodel.MainViewModel

// Adapter-Klasse für die Anzeige von Personen in den Suchergebnissen im Finder
class FinderResultAdapter(
    private val dataset: List<PersonData>, // Liste von PersonData-Objekten als Datensatz für den Adapter
    private val mainViewModel: MainViewModel, // Instanz des MainViewModels für Dateninteraktion
) : RecyclerView.Adapter<FinderResultAdapter.ItemViewHolder>() {

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

        // Abrufen der Clubs aus der ClubDatabase
        var clubs = ClubDatabase().getClubs()

        // Setzen von Texten und Daten für die Anzeige von Personendaten
        holder.binding.tvName.text = item.name
        holder.binding.textStatAge.text = "ALTER"
        holder.binding.tvStatAge.text = item.age
        holder.binding.textStatMatches.text = "MATCHES"
        holder.binding.tvStatMatches.text = item.matches
        holder.binding.textStatPokale.text = "POKALE"
        holder.binding.tvStatPokale.text = item.trophys
        holder.binding.textStatSize.text = "GRÖSSE"
        holder.binding.tvStatSize.text = item.size
        holder.binding.textStatWins.text = "WINS"
        holder.binding.tvStatWins.text = item.wins
        holder.binding.tvEntfernung.text = (item.entfernung.toString() + " km entfernt")
        holder.binding.tvSport.text = item.sportsOne
        holder.binding.tvLevel2.text = item.level
        holder.binding.tvDate.text = item.date
        holder.binding.btClub.text = clubs.value!![item.club!!].name

        holder.binding.btProfile.setOnClickListener {
            mainViewModel.setCurrentProfile(item)
            // Navigation zu einem anderen Profil mit Hilfe des NavController
            holder.itemView.findNavController().navigate(R.id.strangerProfilFragment)
        }
    }
}