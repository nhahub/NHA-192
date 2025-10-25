package com.example.MovieApp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MovieApp.auth.AuthResult
import com.example.MovieApp.auth.EmailPasswordAuthManagerRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: EmailPasswordAuthManagerRepository
) : ViewModel() {

    val tag = "AuthViewModel"

    // Internal mutable state
    private val _signupState = MutableStateFlow<AuthResult<String>>(AuthResult.Loading)
    // Exposed as read-only to UI
    val signupState = _signupState.asStateFlow()

    private val _signInState = MutableStateFlow<AuthResult<String>>(AuthResult.Loading)
    // Exposed as read-only to UI
    val signInState = _signInState.asStateFlow()

    // Function to handle user sign-up
    fun SignUp(email: String, password: String, name: String, phoneNumber: String) {
        Log.d(tag, "Starting sign-up process for email: $email")
        _signupState.value = AuthResult.Loading
        viewModelScope.launch {
            try {
                Log.d(tag, "Coroutine launched for sign-up")
                val result = repo.signUp(email, password, name, phoneNumber)
                _signupState.value = result
            } catch (e: Exception) {
                Log.e(tag, "Error during sign-up: ${e.message}")
                _signupState.value = AuthResult.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    // Function to check if user is Sign in
    fun SignIn(email : String , password : String) {
        Log.d(tag, "Starting sign-in process for email: $email")
        _signInState.value = AuthResult.Loading
        viewModelScope.launch {
            try {
                Log.d(tag, "Coroutine launched for sign-in")
                val result = repo.signIn(email, password)
                _signInState.value = result
            } catch (e: Exception) {
                Log.e(tag, "Error during sign-in: ${e.message}")
                _signInState.value = AuthResult.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    // Function to check if user is logged in
    fun isLoginedIn() = repo.isLoginedIn()

    // Function to Sign Out
    fun signOut() {
        Log.d(tag, "Starting sign-out process")
        repo.signOut()
        Log.d(tag, "User signed out successfully")
    }

    // Function to get current user
    fun getCurrentUser() : FirebaseUser? {
        Log.d(tag, "Getting current user")
        return repo.getCurrentUser()
    }
}
