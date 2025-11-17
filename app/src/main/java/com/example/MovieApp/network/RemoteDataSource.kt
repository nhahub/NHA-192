package com.example.MovieApp.network

import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.dto.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(page: Int) : UiState<List<Movie>>
    suspend fun getUpComingMovies(page: Int) : UiState<List<Movie>>

    suspend fun getTopRatedMovies(page: Int) : UiState<List<Movie>>

}