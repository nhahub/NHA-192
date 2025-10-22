package com.example.MovieApp.network


import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.dto.Movie
import com.example.MovieApp.network.API.apiServices
import retrofit2.HttpException
import java.io.IOException
// This is the implementation of RemoteDataSource interface that fetches popular movies from a remote API.
// It handles different scenarios such as successful responses, server errors, and network issues.
// The function returns a UiState object that represents the current state of the data fetching operation.
// AfterWards, we can use it to fetch the upcoming movies , genres of movies as well by creating a similar function.
class RemoteDataSourceImpl(
) : RemoteDataSource {
    override suspend fun getPopularMovies(page: Int): UiState<List<Movie>> {
        return try {
            val response = apiServices.getPopularMovies(page = page)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    UiState.Success(body.results ?: emptyList())
                } else {
                    UiState.Error("Empty response body is equal to null")
                }
            } else {
                val serverMsg = response.errorBody()?.string()?.takeIf { it.isNotBlank() }
                UiState.Error("HTTP ${response.code()}: ${serverMsg ?: response.message()}")
            }
        }catch (e: IOException) {
            // catch network-related errors
            UiState.Error("No internet connection. Please check your network.")
        } catch (e: HttpException) {
            // catch HTTP protocol errors
            UiState.Error("Server error: ${e.code()}")
        } catch (e: Exception) {
            // catch any other unexpected errors
            UiState.Error("Unexpected error occurred. Please try again later.")
        }
    }
}