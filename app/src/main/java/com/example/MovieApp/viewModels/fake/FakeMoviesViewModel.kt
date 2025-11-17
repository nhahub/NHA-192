package com.example.MovieApp.fake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.dto.Movie
import com.example.MovieApp.repo.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FakeMoviesViewModel(
    private val repo: MoviesRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val popularMovies: StateFlow<UiState<List<Movie>>> = _popularMovies

    private val _topRatedMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val topRatedMovies: StateFlow<UiState<List<Movie>>> = _topRatedMovies

    private val _upComingMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val upComingMovies: StateFlow<UiState<List<Movie>>> = _upComingMovies

    init {
        viewModelScope.launch {
            _popularMovies.value = repo.getPopularMovies(1)
            _topRatedMovies.value = repo.getTopRatedMovies(1)
            _upComingMovies.value = repo.getUpComingMovies(1)
        }
    }
}
