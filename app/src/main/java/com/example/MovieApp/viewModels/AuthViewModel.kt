package com.example.MovieApp.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MovieApp.auth.AuthResult
import com.example.MovieApp.auth.EmailPasswordAuthManagerRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: EmailPasswordAuthManagerRepository
) : ViewModel() {

    val tag = "AuthViewModel"

    // Internal mutable state
    private val _signupState = mutableStateOf<AuthResult<String>>(AuthResult.Loading)
    // Exposed as read-only to UI
    val signupState: State<AuthResult<String>> get() = _signupState

    // Function to handle user sign-up
    fun signUp(email: String, password: String, name: String, phoneNumber: String) {
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
        _signupState.value = AuthResult.Loading
        viewModelScope.launch {
            try {
                Log.d(tag, "Coroutine launched for sign-in")
                val result = repo.signIn(email, password)
                _signupState.value = result
            } catch (e: Exception) {
                Log.e(tag, "Error during sign-in: ${e.message}")
                _signupState.value = AuthResult.Error(e.message ?: "Unknown error occurred")
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
