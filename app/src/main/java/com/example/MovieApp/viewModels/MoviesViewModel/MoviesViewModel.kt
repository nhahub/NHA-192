package com.example.MovieApp.viewModels.MoviesViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.dto.Movie
import com.example.MovieApp.repo.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repo : MoviesRepository
) : ViewModel()  {

    // Popular Movies
    private val _popularMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val popularMovies : StateFlow<UiState<List<Movie>>> = _popularMovies.asStateFlow()


    fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            when (val response = repo.getPopularMovies(page)) {
                is UiState.Success -> _popularMovies.value = UiState.Success(response.data)
                is UiState.Error -> _popularMovies.value = UiState.Error(response.message)
                else -> _popularMovies.value = UiState.Loading
            }
        }
    }

    // Upcoming Movies
    private val _UpComingMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val UpComingMovies : StateFlow<UiState<List<Movie>>> = _UpComingMovies.asStateFlow()

    fun getUpComingMovies(page: Int) {
        viewModelScope.launch {
            when (val response = repo.getUpComingMovies(page)) {
                is UiState.Success -> _UpComingMovies.value = UiState.Success(response.data)
                is UiState.Error -> _UpComingMovies.value = UiState.Error(response.message)
                else -> _UpComingMovies.value = UiState.Loading
            }
        }
    }


    // Top Rated Movies

    private val _TopRatedMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val TopRatedMovies : StateFlow<UiState<List<Movie>>> = _TopRatedMovies.asStateFlow()

    fun getTopRatedMovies(page: Int) {
        viewModelScope.launch {
            when (val response = repo.getTopRatedMovies(page)) {
                is UiState.Success -> _TopRatedMovies.value = UiState.Success(response.data)
                is UiState.Error -> _TopRatedMovies.value = UiState.Error(response.message)
                else -> _TopRatedMovies.value = UiState.Loading
            }
        }
    }

}