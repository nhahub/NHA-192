package com.example.MovieApp.Repo.Movies

import com.example.MovieApp.Dto.Movie
import com.example.MovieApp.Utils.UiState

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): UiState<List<Movie>>
    suspend fun getUpComingMovies(page: Int): UiState<List<Movie>>

    suspend fun getTopRatedMovies(page: Int): UiState<List<Movie>>

    suspend fun getMoviesByGenre(page: Int, genreId: Int): UiState<List<Movie>>

    suspend fun insert(movie: Movie)
    suspend fun getFavoriteMovies(): List<Movie>
    suspend fun getWatchLaterMovies(): List<Movie>
    suspend fun delete(movie: Movie)
}