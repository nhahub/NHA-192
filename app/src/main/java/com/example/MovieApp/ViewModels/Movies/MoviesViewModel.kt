package com.example.MovieApp.ViewModels.Movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MovieApp.Dto.Movie
import com.example.MovieApp.Repo.Movies.MoviesRepository
import com.example.MovieApp.Utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class MoviesViewModel(
    private val repo: MoviesRepository
) : ViewModel() {

    // Popular Movies
    private val _popularMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val popularMovies: StateFlow<UiState<List<Movie>>> = _popularMovies.asStateFlow()


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

    val UpComingMovies: StateFlow<UiState<List<Movie>>> = _UpComingMovies.asStateFlow()

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

    val TopRatedMovies: StateFlow<UiState<List<Movie>>> = _TopRatedMovies.asStateFlow()

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

    val ActionMovies: StateFlow<UiState<List<Movie>>> = _ActionMovies.asStateFlow()

    fun getActionMovies(page: Int) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page, genreId = 28)) {
                is UiState.Success -> _ActionMovies.value = UiState.Success(response.data)
                is UiState.Error -> _ActionMovies.value = UiState.Error(response.message)
                else -> _ActionMovies.value = UiState.Loading
            }
        }
    }

    // Adventure Movies ---> (12)

    private val _AdventureMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val AdventureMovies: StateFlow<UiState<List<Movie>>> = _AdventureMovies.asStateFlow()

    fun getAdventureMovies(page: Int) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page, genreId = 12)) {
                is UiState.Success -> _AdventureMovies.value = UiState.Success(response.data)
                is UiState.Error -> _AdventureMovies.value = UiState.Error(response.message)
                else -> _AdventureMovies.value = UiState.Loading
            }
        }
    }

    // Comedy Movies ---> (35)

    private val _ComedyMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val ComedyMovies: StateFlow<UiState<List<Movie>>> = _ComedyMovies.asStateFlow()

    fun getComedyMovies(page: Int) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page, genreId = 35)) {
                is UiState.Success -> _ComedyMovies.value = UiState.Success(response.data)
                is UiState.Error -> _ComedyMovies.value = UiState.Error(response.message)
                else -> _ComedyMovies.value = UiState.Loading
            }
        }
    }

    // Fantasy ---> (14)

    private val _FantasyMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)

    val FantasyMovies: StateFlow<UiState<List<Movie>>> = _FantasyMovies.asStateFlow()

    fun getFantasyMovies(page: Int) {
        viewModelScope.launch {
            when (val response = repo.getMoviesByGenre(page = page, genreId = 14)) {
                is UiState.Success -> _FantasyMovies.value = UiState.Success(response.data)
                is UiState.Error -> _FantasyMovies.value = UiState.Error(response.message)
                else -> _FantasyMovies.value = UiState.Loading
            }
        }
    }


    // Favorite Movies

    private val _FavoriteMovies = MutableStateFlow<List<Movie>>(emptyList())

    val FavoriteMovies: StateFlow<List<Movie>> = _FavoriteMovies.asStateFlow()

    // for getting the favorite movies from the database
    fun getFavoriteMovies() {
        viewModelScope.launch {
            _FavoriteMovies.value = repo.getFavoriteMovies()
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            movie.isFavorite = !movie.isFavorite
            if (movie.isFavorite || movie.isWatchLater) {
                repo.insert(movie)
            } else {
                repo.delete(movie)
            }
            // تحديث الـ list في ViewModel فورًا
            _FavoriteMovies.value = repo.getFavoriteMovies()
        }
    }

    // WatchLater Movies

    private val _WatchLaterMovies = MutableStateFlow<List<Movie>>(emptyList())

    val WatchLaterMovies: StateFlow<List<Movie>> = _WatchLaterMovies.asStateFlow()

    // for getting the watch later movies from the database

    fun getWatchLaterMovies() {
        viewModelScope.launch {
            _WatchLaterMovies.value = repo.getWatchLaterMovies()
        }
    }

    fun toggleWatchLater(movie: Movie) {
        viewModelScope.launch {
            movie.isWatchLater = !movie.isWatchLater
            if (movie.isFavorite || movie.isWatchLater) {
                repo.insert(movie)
            } else {
                repo.delete(movie)
            }
            _WatchLaterMovies.value = repo.getWatchLaterMovies()
        }
    }

    // for inserting the movie in the database
    fun insertInDataBase(movie: Movie) {
        viewModelScope.launch {
            repo.insert(movie)
        }
    }

    // for deleting the movie from the database
    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repo.delete(movie)
        }
    }

    // Search Logic: Merges Action, Adventure, Comedy, and Fantasy movies
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    // Combine all genres first (helper flow) to avoid combine 5+ args issue
    private val _mergedGenreMovies = combine(
        _ActionMovies,
        _AdventureMovies,
        _ComedyMovies,
        _FantasyMovies
    ) { action, adventure, comedy, fantasy ->
        val movies = mutableListOf<Movie>()
        fun add(state: UiState<List<Movie>>) {
            if (state is UiState.Success) movies.addAll(state.data)
        }
        add(action)
        add(adventure)
        add(comedy)
        add(fantasy)
        movies.distinctBy { it.id }
    }


    // Helper to trigger loading of all sections needed for search
    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie.asStateFlow()

    // Function to SET the movie (call this when user clicks a card)
    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.value = movie
    }
}