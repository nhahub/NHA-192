package com.example.MovieApp.Repo.Search

import com.example.MovieApp.Network.RemoteDataSource
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.Dto.Movie

class SearchRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): SearchRepository {
    override suspend fun searchMovies(query: String, page: Int): UiState<List<Movie>> {
        return remoteDataSource.searchMovies(query,page)
    }

}