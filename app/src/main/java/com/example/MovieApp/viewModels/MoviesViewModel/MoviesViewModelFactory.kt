package com.example.MovieApp.viewModels.MoviesViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MovieApp.repo.MoviesRepository

class MoviesViewModelFactory(
    private val repo: MoviesRepository
)
: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            MoviesViewModel(repo) as T
        } else {
            throw IllegalArgumentException("MoviesViewModel Not Found")
        }
    }
}