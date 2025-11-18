package com.example.MovieApp.viewModels.fake

import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.dto.Movie
import com.example.MovieApp.repo.MoviesRepository

class FakeMoviesRepository : MoviesRepository {
    override suspend fun getPopularMovies(page: Int): UiState<List<Movie>> {
        return UiState.Success(
            listOf(
                Movie(
                    id = 1,
                    adult = false,
                    backdrop_path = "",
                    genre_ids = listOf(28, 12),
                    original_language = "en",
                    original_title = "Fake Popular Movie",
                    overview = "This is a fake popular movie.",
                    popularity = 100.0,
                    poster_path = "",
                    release_date = "2025-01-01",
                    title = "Popular Fake",
                    video = false,
                    vote_average = 7.5,
                    vote_count = 1000
                )
            )
        )
    }

    override suspend fun getTopRatedMovies(page: Int): UiState<List<Movie>> {
        return UiState.Success(
            listOf(
                Movie(
                    id = 2,
                    adult = false,
                    backdrop_path = "",
                    genre_ids = listOf(18),
                    original_language = "en",
                    original_title = "Fake Top Rated Movie",
                    overview = "This is a fake top-rated movie.",
                    popularity = 200.0,
                    poster_path = "",
                    release_date = "2025-02-01",
                    title = "Top Rated Fake",
                    video = false,
                    vote_average = 8.7,
                    vote_count = 1500
                )
            )
        )
    }

    override suspend fun getMoviesByGenre(
        page: Int,
        genreId: Int
    ): UiState<List<Movie>> {
        return UiState.Success(
            listOf(
                Movie(
                    id = 2,
                    adult = false,
                    backdrop_path = "",
                    genre_ids = listOf(18),
                    original_language = "en",
                    original_title = "Fake Top Rated Movie",
                    overview = "This is a fake top-rated movie.",
                    popularity = 200.0,
                    poster_path = "",
                    release_date = "2025-02-01",
                    title = "Top Rated Fake",
                    video = false,
                    vote_average = 8.7,
                    vote_count = 1500
                )
            )
        )
    }

    override suspend fun getUpComingMovies(page: Int): UiState<List<Movie>> {
        return UiState.Success(
            listOf(
                Movie(
                    id = 3,
                    adult = false,
                    backdrop_path = "",
                    genre_ids = listOf(16),
                    original_language = "en",
                    original_title = "Fake Upcoming Movie",
                    overview = "This is a fake upcoming movie.",
                    popularity = 50.0,
                    poster_path = "",
                    release_date = "2025-03-01",
                    title = "Upcoming Fake",
                    video = false,
                    vote_average = 6.0,
                    vote_count = 300
                )
            )
        )
    }
}
