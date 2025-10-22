package com.example.MovieApp.network

import com.example.MovieApp.dto.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "8375062ce126aac7379b665b2af3d0ed",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MovieResponse>
}