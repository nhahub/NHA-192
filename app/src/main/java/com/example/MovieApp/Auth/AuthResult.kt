package com.example.MovieApp.Auth

sealed class AuthResult<out T> {
    object Loading : AuthResult<Nothing>()
    data class Success<out T>(val data: T) : AuthResult<T>()
    data class Error(val message: String) : AuthResult<Nothing>()
}