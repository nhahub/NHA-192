package com.example.MovieApp.ViewModels.Movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MovieApp.Repo.Movies.MoviesRepository

class MoviesViewModelFactory(
    private val repo: MoviesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            MoviesViewModel(repo) as T
        } else {
            throw IllegalArgumentException("MoviesViewModel Not Found")
        }
    }
}