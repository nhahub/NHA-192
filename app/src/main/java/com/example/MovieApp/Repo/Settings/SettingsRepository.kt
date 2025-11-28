package com.example.MovieApp.Repo.Settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val darkMode: Flow<Boolean>

    suspend fun setDarkMode(enabled: Boolean)
}