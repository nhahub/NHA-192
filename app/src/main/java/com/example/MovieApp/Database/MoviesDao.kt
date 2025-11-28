package com.example.MovieApp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.MovieApp.Dto.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    suspend fun getFavoriteMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE isWatchLater = 1")
    suspend fun getWatchLaterMovies(): List<Movie>

    @Delete
    suspend fun delete(movie: Movie)
}
