package com.example.myfirstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder>() {
    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvYear: TextView = itemView.findViewById(R.id.tv_item_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_list, parent, false)

        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val currentMovie = movieList[position]

        holder.tvTitle.text = currentMovie.title
        holder.tvYear.text = currentMovie.year.toString()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

