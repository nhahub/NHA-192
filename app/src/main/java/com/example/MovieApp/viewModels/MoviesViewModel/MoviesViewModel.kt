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


    // Action Movies ---> (28)

    private val _ActionMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val ActionMovies : StateFlow<UiState<List<Movie>>> = _ActionMovies.asStateFlow()

    fun getActionMovies(page: Int ) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page , genreId = 28)) {
                is UiState.Success -> _ActionMovies.value = UiState.Success(response.data)
                is UiState.Error -> _ActionMovies.value = UiState.Error(response.message)
                else -> _ActionMovies.value = UiState.Loading
            }
        }
    }

    // Adventure Movies ---> (12)

    private val _AdventureMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val AdventureMovies : StateFlow<UiState<List<Movie>>> = _AdventureMovies.asStateFlow()

    fun getAdventureMovies(page: Int ) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page , genreId = 12)) {
                is UiState.Success -> _AdventureMovies.value = UiState.Success(response.data)
                is UiState.Error -> _AdventureMovies.value = UiState.Error(response.message)
                else -> _AdventureMovies.value = UiState.Loading
            }
        }
    }

    // Comedy Movies ---> (35)

    private val _ComedyMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val ComedyMovies : StateFlow<UiState<List<Movie>>> = _ComedyMovies.asStateFlow()

    fun getComedyMovies(page: Int ) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page , genreId = 35)) {
                is UiState.Success -> _ComedyMovies.value = UiState.Success(response.data)
                is UiState.Error -> _ComedyMovies.value = UiState.Error(response.message)
                else -> _ComedyMovies.value = UiState.Loading
            }
        }
    }

    // Fantasy ---> (14)

    private val _FantasyMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val FantasyMovies : StateFlow<UiState<List<Movie>>> = _FantasyMovies.asStateFlow()

    fun getFantasyMovies(page: Int ) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page , genreId = 14)) {
                is UiState.Success -> _FantasyMovies.value = UiState.Success(response.data)
                is UiState.Error -> _FantasyMovies.value = UiState.Error(response.message)
                else -> _FantasyMovies.value = UiState.Loading
            }
        }
    }

}