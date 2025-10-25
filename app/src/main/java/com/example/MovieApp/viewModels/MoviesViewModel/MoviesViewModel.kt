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

    private val _popularMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val popularMovies : StateFlow<UiState<List<Movie>>> = _popularMovies.asStateFlow()

    fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            val response = repo.getPopularMovies(page)

            when (response) {
                is UiState.Success -> _popularMovies.value = UiState.Success(response.data)
                is UiState.Error -> _popularMovies.value = UiState.Error(response.message)
                else -> _popularMovies.value = UiState.Loading
            }
        }
    }
}