package com.example.MovieApp.Repo.Search

import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.Dto.Movie

interface SearchRepository {
    suspend fun searchMovies(query: String, page: Int): UiState<List<Movie>>
}