package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.NewsHomeListItemBinding
import com.example.myapplication.model.News

// Adapter-Klasse für die Anzeige von News auf der Startseite
class NewsHomeAdapter(
    private val dataset: List<News> // Liste von News-Objekten als Datensatz für den Adapter
) : RecyclerView.Adapter<NewsHomeAdapter.ItemViewHolder>() {

    // View Holder-Klasse, die die Darstellung eines einzelnen Elements in der RecyclerView hält
    inner class ItemViewHolder(val binding: NewsHomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Methode zur Erstellung eines neuen View Holders
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsHomeAdapter.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders News") // Log-Ausgabe für das Binding des View Holders
        val binding =
            NewsHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // Methode zur Anpassung der Inhalte eines View Holders basierend auf seiner Position im Datensatz
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        Log.d("TAG", "Adapter hat datenset gezogen")

        // Setzen von Texten und Daten für die Anzeige von News
        holder.binding.newsTitle.text = item.title
        holder.binding.newsText.text = item.text
    }

    // Methode zur Abrufung der Anzahl der Elemente im Datensatz
    override fun getItemCount(): Int {
        return dataset.size
    }
}
