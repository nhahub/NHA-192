package com.example.MovieApp.database

import com.example.MovieApp.dto.Movie

interface LocalDataSource {

    suspend fun insert(movie: Movie)
    suspend fun getFavoriteMovies(): List<Movie>
    suspend fun getWatchLaterMovies(): List<Movie>
    suspend fun delete(movie: Movie)

}