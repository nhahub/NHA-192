# DragoBlaze - Android Movie Streaming Application 

# Overview
DragoBlazo is a feature-rich Android application that allows users to browse, search, and explore movies across multiple genres.
The application is designed in a Chinese-style visual theme, incorporating clean typography, rounded card patterns, bold visuals, and elegant animations influenced by modern Chinese mobile UI trends.

DragoBlazo integrates:

  - Real-time API calls

  - Firebase authentication

  - Room database for offline favorites & watch-later

  - Modern Jetpack Compose UI

  - MVVM architecture for scalability

  - Unit tests for core components

This project demonstrates a professional, production-level Android app architecture suitable for large-scale applications.

# Features
- Screens

- Home Screen (displays trending & featured movies)

  - Search Screen (search movies across all genres)

  - Movie Details Screen (Title, Poster, Cast, Director, Overview)

  - Popular Genres Screens (Action, Comedy, Fantasy, Science Fiction)

  - Settings Screen (theme, profile, preferences)

- Authentication

  - Firebase Authentication (Email/Password login & registration)

- User Collections

  - Favorites Screen —> saved using Room Database

  - Watch Later Screen —> persistent local storage via Room

- Network

  - Real-time API fetching using Retrofit + Coroutines

  - Loading and error handling UI

- Testing

  - Unit tests for ViewModels and repositories

  - Testable architecture using dependency injection principles

- Architecture

  - Fully structured in MVVM with Repository pattern

  - Reactive state handling using StateFlow

# Tech Stack
- Programming Language: Kotlin.
- UI Framework: Jetpack Compose
- Architecture: MVVM + Repository pattern
- Network: Retrofit, Gson
- Image Loading: Coil
- Coroutines: StateFlow, suspend functions
- Dependency Injection: (Optional: Hilt/Koin)
- Build Tools: Gradle (KTS recommended)

# Setup
- Clone the repository:
  - git clone https://github.com/nhahub/NHA-192.git

- Open in Android Studio
- Add your API key in local.properties
- Run the app on emulator or device
