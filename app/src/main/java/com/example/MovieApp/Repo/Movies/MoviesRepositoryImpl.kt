package com.example.MovieApp.Repo.Movies


import com.example.MovieApp.Network.RemoteDataSource
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.Database.LocalDataSource
import com.example.MovieApp.Dto.Movie
import com.example.MovieApp.Dto.CreditsResponse

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
    override suspend fun getMovieCredits(movieId: Int): UiState<CreditsResponse> {
        return remoteDataSource.getMovieCredits(movieId)
    }
}