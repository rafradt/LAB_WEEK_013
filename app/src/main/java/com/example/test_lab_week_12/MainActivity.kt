package com.example.test_lab_week_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_lab_week_12.databinding.ActivityMainBinding
import com.example.test_lab_week_12.model.Movie

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val movieAdapter = MovieAdapter(object : MovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                openMovieDetails(movie)
            }
        })

        binding.movieList.adapter = movieAdapter

        val movieRepository =
            (application as MovieApplication).movieRepository

        val movieViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(movieRepository) as T
                }
            }
        )[MovieViewModel::class.java]

        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this
    }

    private fun openMovieDetails(movie: Movie) {
        // isi kalau mau, boleh kosong untuk LAB
    }
}
