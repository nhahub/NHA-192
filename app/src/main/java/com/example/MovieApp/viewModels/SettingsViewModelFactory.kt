package com.example.MovieApp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MovieApp.repo.SettingsRepository

class SettingsViewModelFactory(
    private val repository: SettingsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SettingsViewModel :: class. java)) {
            SettingsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("SettingsViewModel class not found")
        }
    }

}