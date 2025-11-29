package com.example.MovieApp.Dto

data class CreditsResponse(
    val cast: List<Cast?>?,
    val crew: List<Crew?>?,
    val id: Int?
)