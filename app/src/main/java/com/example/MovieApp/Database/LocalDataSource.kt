package com.example.MovieApp.Database

import com.example.MovieApp.Dto.Movie

interface LocalDataSource {

    suspend fun insert(movie: Movie)
    suspend fun getFavoriteMovies(): List<Movie>
    suspend fun getWatchLaterMovies(): List<Movie>
    suspend fun delete(movie: Movie)

}