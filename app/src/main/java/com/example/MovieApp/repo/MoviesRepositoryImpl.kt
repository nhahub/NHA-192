package com.example.MovieApp.repo

import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.dto.Movie
import com.example.MovieApp.network.RemoteDataSource

class MoviesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
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
}