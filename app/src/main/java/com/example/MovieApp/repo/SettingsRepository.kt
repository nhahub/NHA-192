package com.example.MovieApp.repo

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val darkMode: Flow<Boolean>

    suspend fun setDarkMode(enabled: Boolean)
}