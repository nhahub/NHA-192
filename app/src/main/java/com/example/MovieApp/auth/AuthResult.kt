package com.example.MovieApp.auth

import com.example.MovieApp.Utils.UiState

sealed class AuthResult<out T> {
    object Loading : AuthResult<Nothing>()
    data class Success<out T>(val data: T) : AuthResult<T>()
    data class Error(val message: String) : AuthResult<Nothing>()
}