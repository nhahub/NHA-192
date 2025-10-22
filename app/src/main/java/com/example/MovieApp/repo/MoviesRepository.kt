package com.example.MovieApp.repo

import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.dto.Movie

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int) : UiState<List<Movie>>
}