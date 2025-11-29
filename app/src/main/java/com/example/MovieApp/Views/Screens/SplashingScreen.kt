package com.example.MovieApp.Views.Screens

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.MovieApp.Auth.EmailPasswordAuthManagerRepository
import com.example.MovieApp.Database.LocalDataSourceImpl
import com.example.MovieApp.Datastore.DataStoreManager
import com.example.MovieApp.Network.RemoteDataSourceImpl
import com.example.MovieApp.R
import com.example.MovieApp.Repo.Movies.MoviesRepositoryImpl
import com.example.MovieApp.Repo.Search.SearchRepositoryImpl
import com.example.MovieApp.Repo.Settings.SettingsRepositoryImpl
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.ViewModels.Auth.AuthViewModel
import com.example.MovieApp.ViewModels.Auth.AuthViewModelFactory
import com.example.MovieApp.ViewModels.Movies.MoviesViewModel
import com.example.MovieApp.ViewModels.Movies.MoviesViewModelFactory
import com.example.MovieApp.ViewModels.Search.SearchViewModel
import com.example.MovieApp.ViewModels.Search.SearchViewModelFactory
import com.example.MovieApp.ViewModels.Settings.SettingsViewModel
import com.example.MovieApp.ViewModels.Settings.SettingsViewModelFactory
import com.example.MovieApp.ui.themes.SplashScreenTheme
import kotlinx.coroutines.delay

class SplashingScreen : ComponentActivity() {
    val customFont = FontFamily(Font(R.font.spellofasia))

    // creating view model instance by factory design pattern
    val viewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(
            repo = MoviesRepositoryImpl(
                remoteDataSource = RemoteDataSourceImpl(),
                localDataSource = LocalDataSourceImpl(this)
            )
        )
    }

    val searchViewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(
            repo = SearchRepositoryImpl(
                remoteDataSource = RemoteDataSourceImpl(),
            )
        )
    }

    val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(
            repo = EmailPasswordAuthManagerRepository()
        )
    }

    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(
            repository = SettingsRepositoryImpl(
                dataStoreManager = DataStoreManager(this)
            )
        )
    }

    // Ahmed : api key ->  8375062ce126aac7379b665b2af3d0ed
    // use your own api key as there are limitations around the number of requests by Tmdb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SplashScreenTheme {
                NavHost(
                    navController = navController,
                    startDestination = "splash_screen"
                ) {
                    composable("splash_screen") {
                        SplashingScreen(
                            viewModel = viewModel,
                            navController = navController,
                            authViewModel = authViewModel
                        )
                    }
                    composable("auth_screen") {
                        AuthScreen(navController = navController)
                    }
                    composable("sign_up_screen") {
                        SignUpScreen(
                            authViewModel = authViewModel,
                            navController = navController
                        )
                    }
                    composable("sign_in_screen") {
                        SignInScreen(
                            authViewModel = authViewModel,
                            navController = navController
                        )
                    }
                    composable("main_screen") {
                        MainScreen(
                            viewModel = viewModel,
                            parentNavController = navController
                        )
                    }
                }
            }
        }
    }
}

// Splashing Screen Composable function
@Composable
fun SplashingScreen(
    viewModel: MoviesViewModel,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    // first i instantiate the ui state by collecting the state flow from view model
    val popularMoviesState by viewModel.popularMovies.collectAsStateWithLifecycle()
    val topRatedMoviesState by viewModel.TopRatedMovies.collectAsStateWithLifecycle()
    val upcomingMoviesState by viewModel.UpComingMovies.collectAsStateWithLifecycle()

    // then i call the get popular movies function to fetch data from api which will make the data
    // ready for the user before entering the main screen
    // those popular movies will be stored in the database
    LaunchedEffect(Unit) {
        viewModel.getPopularMovies(1)
        viewModel.getTopRatedMovies(1)
        viewModel.getUpComingMovies(1)
    }

    // Define the Combined UI State based on the individual state
    val combinedUiState = remember(popularMoviesState, topRatedMoviesState, upcomingMoviesState) {
        when {
            // Priority 1: Any Error? -> Show Error
            popularMoviesState is UiState.Error -> popularMoviesState
            topRatedMoviesState is UiState.Error -> topRatedMoviesState
            upcomingMoviesState is UiState.Error -> upcomingMoviesState

            // Priority 2: All Success? -> Show Success
            popularMoviesState is UiState.Success &&
                    topRatedMoviesState is UiState.Success &&
                    upcomingMoviesState is UiState.Success -> UiState.Success(emptyList()) // مش محتاجين الداتا هنا أوي قد ما محتاجين الحالة

            // Priority 3: Else -> Loading
            else -> UiState.Loading
        }
    }
    // i made a box to make an overlay screen over the background image
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.onboardingscreen1),
            contentDescription = "splash screen image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        var showButton by remember { mutableStateOf(false) }

        // When uiState is Success, launch a coroutine to delay showing the button
        LaunchedEffect(combinedUiState) {
            if (combinedUiState is UiState.Success) {
                delay(2000) // delay 1 second
                showButton = true
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        )

        // Content overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // or Top if you want top
        ) {
            Text(
                text = "DragoBlaze",
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont
            )
            Text(
                text = "Discover and Explore Movies for free",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = customFont
            )
            when {
                (combinedUiState is UiState.Loading && !showButton) || (combinedUiState is UiState.Success && !showButton) -> {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Loading...",
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                combinedUiState is UiState.Success -> {
                    Button(
                        onClick = {
                            if (authViewModel.isLoginedIn()) {
                                navController.navigate("main_screen") {
                                    popUpTo("splash_screen") { inclusive = true }
                                }
                            } else {
                                navController.navigate("auth_screen")
                            }
                        },
                        shape = RoundedCornerShape(28.dp), // Large rounded corners for the pill shape
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF334455), // The dark, moody background
                            contentColor = Color.White // White text color
                        ),
                        border = BorderStroke(1.dp, Color(0x66FFFFFF)), // The light, shiny outline
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp, // High elevation for a lifted/glossy look
                            pressedElevation = 2.dp // Sinks when pressed
                        )
                    ) {
                        Text(text = "Continue")
                    }
                }

                combinedUiState is UiState.Error -> {
                    Text(text = "Error: ${combinedUiState.message}")
                }
            }
        }
    }
}

// Preview function to visualize the SplashingScreen composable to see how it looks
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    val fakeViewModel = MoviesViewModelFactory(
        repo = MoviesRepositoryImpl(
            RemoteDataSourceImpl(),
            LocalDataSourceImpl(LocalContext.current)
        )
    ).create(MoviesViewModel::class.java)

    val fakeauthViewModel = AuthViewModelFactory(
        repo = EmailPasswordAuthManagerRepository()
    ).create(AuthViewModel::class.java)


    SplashingScreen(
        viewModel = fakeViewModel,
        navController = NavController(LocalContext.current),
        authViewModel = fakeauthViewModel
    )
}