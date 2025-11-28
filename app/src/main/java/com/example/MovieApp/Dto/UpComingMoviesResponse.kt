package com.example.MovieApp.Dto

data class UpComingMoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)