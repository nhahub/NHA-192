package com.example.MovieApp.ViewModels.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MovieApp.Repo.Search.SearchRepository
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.Dto.Movie
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repo: SearchRepository): ViewModel() {
    private val _searchedMovie = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val searchedMovie : StateFlow<UiState<List<Movie>>> = _searchedMovie.asStateFlow()

    private val _query = MutableStateFlow("")
    val query : StateFlow<String> = _query.asStateFlow()

    private var searchJob: Job? = null
    fun searchForAMovie(query: String, page: Int) {
        viewModelScope.launch {
            when (val response = repo.searchMovies(query, page)) {
                is UiState.Success -> _searchedMovie.value = UiState.Success(response.data)
                is UiState.Error -> _searchedMovie.value = UiState.Error(response.message)
                else -> _searchedMovie.value = UiState.Loading
            }
        }
    }

    fun onSearchTextChange(text: String) {

        _query.value = text
        // cancel previous typing search
        searchJob?.cancel()

        // start new debounce timer
        searchJob = viewModelScope.launch {
            delay(500) // wait for user to finish typing
            if (text.isNotBlank()) {
                searchForAMovie(text, page = 1)
            }
        }
    }


}