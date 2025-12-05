package com.example.MovieApp.ViewModels.Movies

import com.example.MovieApp.Repo.Movies.MoviesRepository
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.Dto.Movie
import com.example.MovieApp.ViewModels.Movies.MoviesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class MoviesViewModelTest {

    //givenSuccess_whenGetPopularMovies_thenSuccessState
    private val repo = mockk<MoviesRepository>()
    private val viewModel = MoviesViewModel(repo)

    @Test
    fun  Popular_Test() = runTest {

        // GIVEN
        val movie = Movie(
            id = 1,
            adult = false,
            backdrop_path = "/path.jpg",
            genre_ids = listOf(12, 16),
            original_language = "en",
            original_title = "Original Title",
            overview = "Nice movie",
            popularity = 9.5,
            poster_path = "/poster.jpg",
            release_date = "2024-01-01",
            title = "Movie A",
            video = false,
            vote_average = 8.0,
            vote_count = 100,
            isFavorite = false,
            isWatchLater = false
        )

        val movies = listOf(movie)

        coEvery { repo.getPopularMovies(1) } returns UiState.Success(movies)

        // WHEN
        viewModel.getPopularMovies(1)

        // THEN
        assertEquals(
            UiState.Success(movies),
            viewModel.popularMovies.value
        )
    }
}