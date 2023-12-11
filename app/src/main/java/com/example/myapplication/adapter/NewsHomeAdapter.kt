package com.example.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.NewsHomeListItemBinding
import com.example.myapplication.model.News

class NewsHomeAdapter(
    private val dataset: List<News>
) : RecyclerView.Adapter<NewsHomeAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(val binding: NewsHomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsHomeAdapter.ItemViewHolder {
        Log.d("TAG", "Binding des ItemViewHolders News")
        val binding =
            NewsHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        Log.d("TAG", "Adapter hat datenset gezogen")

        holder.binding.newsTitle.text = item.title
        holder.binding.newsText.text = item.text
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
