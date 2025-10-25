package com.example.MovieApp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MovieApp.auth.EmailPasswordAuthManagerRepository

class AuthViewModelFactory(
    private val repo: EmailPasswordAuthManagerRepository
)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            AuthViewModel(repo) as T
        } else {
            throw IllegalArgumentException("AuthViewModel Not Found")
        }
    }
}