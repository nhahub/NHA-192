package com.example.MovieApp.ViewModels.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MovieApp.Repo.Search.SearchRepository

class SearchViewModelFactory(private val repo: SearchRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchViewModel:: class. java)) {
            SearchViewModel(repo) as T
        } else {
            throw IllegalArgumentException("SearchViewModel class not found")
        }
    }
}