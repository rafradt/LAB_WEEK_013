package com.example.test_lab_week_12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_lab_week_12.databinding.ViewMovieItemBinding
import com.example.test_lab_week_12.model.Movie

class MovieAdapter(
    private val listener: MovieClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    fun addMovies(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(
        private val binding: ViewMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            itemView.setOnClickListener {
                listener.onMovieClick(movie)
            }
        }
    }
}
