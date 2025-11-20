package com.example.MovieApp.network

import com.example.MovieApp.BuildConfig
import com.example.MovieApp.dto.MovieResponse
import com.example.MovieApp.dto.UpComingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    // Popular Movies
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MovieResponse>

    // Upcoming Movies
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<UpComingMoviesResponse>

    // Top Rated Movies
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MovieResponse>

    // Get Movies
    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
        @Query("with_genres") genreId: Int = 28,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>
}