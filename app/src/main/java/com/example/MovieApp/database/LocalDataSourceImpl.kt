package com.example.MovieApp.database

import android.content.Context
import com.example.MovieApp.dto.Movie

class LocalDataSourceImpl(context: Context) : LocalDataSource {
    private var dao : MoviesDao

    init {
        val db = MoviesDataBase.getInstance(context)
        dao = db.moviesDao()
    }

    override suspend fun insert(movie: Movie) {
        return dao.insert(movie)
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return dao.getFavoriteMovies()
    }

    override suspend fun getWatchLaterMovies(): List<Movie> {
        return dao.getWatchLaterMovies()
    }

    override suspend fun delete(movie: Movie) {
         dao.delete(movie)
    }
}