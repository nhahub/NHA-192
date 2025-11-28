package com.example.MovieApp.Network

import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.Dto.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(page: Int) : UiState<List<Movie>>
    suspend fun getUpComingMovies(page: Int) : UiState<List<Movie>>

    suspend fun getTopRatedMovies(page: Int) : UiState<List<Movie>>

    suspend fun getMoviesByGenre(genreId: Int, page: Int) : UiState<List<Movie>>

    suspend fun searchMovies(query: String, page: Int) : UiState<List<Movie>>

}