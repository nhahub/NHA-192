package com.example.MovieApp.repo

import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.database.LocalDataSource
import com.example.MovieApp.dto.Movie
import com.example.MovieApp.network.RemoteDataSource

class MoviesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): MoviesRepository {
    override suspend fun getPopularMovies(page: Int): UiState<List<Movie>> {
        return remoteDataSource.getPopularMovies(page)
    }

    override suspend fun getUpComingMovies(page: Int): UiState<List<Movie>> {
        return remoteDataSource.getUpComingMovies(page = page)
    }

    override suspend fun getTopRatedMovies(page: Int): UiState<List<Movie>> {
        return remoteDataSource.getTopRatedMovies(page = page)
    }

    override suspend fun getMoviesByGenre(
        page: Int,
        genreId: Int
    ): UiState<List<Movie>> {
        return remoteDataSource.getMoviesByGenre(page = page, genreId = genreId)
    }

    override suspend fun insert(movie: Movie) {
        return localDataSource.insert(movie)
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return localDataSource.getFavoriteMovies()
    }

    override suspend fun getWatchLaterMovies(): List<Movie> {
        return localDataSource.getWatchLaterMovies()
    }

    override suspend fun delete(movie: Movie) {
        return localDataSource.delete(movie)
    }
}