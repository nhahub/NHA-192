package com.example.MovieApp.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class EmailPasswordAuthManagerRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    val tag = "EmailPasswordAuth"
    // Check if user is logged in
    fun isLoginedIn() = auth.currentUser != null

    // Sign Up with Email and Password
    // adding the user's name and phone number to Firestore to fetch it when it is used in the app
    suspend fun signUp(email: String, password: String , name : String , phoneNumber : String ): AuthResult<String> {
        Log.d(tag, "Starting sign-up process for email: $email")
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Log.d(tag, "User created successfully")
            val userid = auth.currentUser?.uid ?: ""
            Log.d("SignUp", "User created with UID: $userid")
            val userData = mapOf(
                "name" to name,
                "phone" to phoneNumber,
                "email" to email
            )
            db.collection("users").document(userid).set(userData).await()
            Log.d(tag, "User data added to Firestore for UID: $userid")
            AuthResult.Success("Account created successfully!")
        } catch (e: Exception) {
            Log.e(tag, "Error during sign-up: ${e.message}")
            AuthResult.Error(e.message ?: "Unknown error occurred")
        } catch (e: CancellationException) {
            Log.d("CancellationException", "Coroutine was cancelled")
            throw e
        }catch (firebaseAuthException: FirebaseAuthException) {
            Log.e(tag, "FirebaseAuthException during sign-up: ${firebaseAuthException.message}")
            AuthResult.Error(firebaseAuthException.message ?: "Authentication error occurred by firebase")
        }
    }

        // Sign In with Email and Password
        suspend fun signIn(email: String, password: String): AuthResult<String> {
            Log.d(tag, "Starting sign-in process for email: $email")
            return try {
                auth.signInWithEmailAndPassword(email, password).await()
                Log.d(tag, "User signed in successfully")
                AuthResult.Success("Login successful!")
            } catch (e: Exception) {
                AuthResult.Error(e.message ?: "Login failed")
            } catch (e: CancellationException) {
                Log.d("CancellationException", "Coroutine was cancelled")
                throw e
            } catch (firebaseAuthException: FirebaseAuthException) {
                Log.e(tag, "FirebaseAuthException during sign-in: ${firebaseAuthException.message}")
                AuthResult.Error(
                    firebaseAuthException.message ?: "Authentication error occurred by firebase"
                )
            }
        }

        // Sign Out
        fun signOut() {
            auth.signOut()
        }

        // Get Current User
        fun getCurrentUser(): FirebaseUser? = auth.currentUser
}