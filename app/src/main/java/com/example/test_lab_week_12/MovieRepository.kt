package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.database.MovieDatabase
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) {

    private val apiKey = "5f2a6337319819e2fe92773701c79f4d"

    fun fetchMovies(): Flow<List<Movie>> = flow {
        val movieDao = movieDatabase.movieDao()

        val savedMovies = movieDao.getMovies()

        if (savedMovies.isEmpty()) {
            val moviesFromApi = movieService.getPopularMovies(apiKey).results

            movieDao.addMovies(moviesFromApi)

            emit(moviesFromApi)
        } else {
            emit(savedMovies)
        }
    }.flowOn(Dispatchers.IO)
}
