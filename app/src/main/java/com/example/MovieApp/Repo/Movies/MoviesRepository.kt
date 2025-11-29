package com.example.MovieApp.Repo.Movies

import com.example.MovieApp.Dto.CreditsResponse
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.Dto.Movie

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int) : UiState<List<Movie>>
    suspend fun getUpComingMovies(page: Int) : UiState<List<Movie>>

    suspend fun getTopRatedMovies(page: Int) : UiState<List<Movie>>

    suspend fun getMoviesByGenre(page : Int , genreId : Int) : UiState<List<Movie>>
//access viewmodel for calling
    suspend fun getMovieCredits(movieId: Int): UiState<CreditsResponse>

    suspend fun insert(movie: Movie)
    suspend fun getFavoriteMovies(): List<Movie>
    suspend fun getWatchLaterMovies(): List<Movie>
    suspend fun delete(movie: Movie)
}