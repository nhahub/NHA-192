package com.example.MovieApp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MovieApp.repo.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository
): ViewModel() {

    val darkMode: StateFlow<Boolean> = repository.darkMode.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Companion.Lazily,
        initialValue = false
    )

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setDarkMode(enabled)
        }
    }

}