package com.example.MovieApp.Repo.Settings

import com.example.MovieApp.Datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val dataStoreManager: DataStoreManager
): SettingsRepository {
    override val darkMode: Flow<Boolean> = dataStoreManager.darkModeFlow
    override suspend fun setDarkMode(enabled: Boolean) = dataStoreManager.setDarkMode(enabled)
}