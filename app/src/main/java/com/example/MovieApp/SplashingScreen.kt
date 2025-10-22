package com.example.MovieApp

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.MovieApp.MoviesViewModel.MoviesViewModel
import com.example.MovieApp.MoviesViewModel.MoviesViewModelFactory
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.network.RemoteDataSourceImpl
import com.example.MovieApp.repo.MoviesRepositoryImpl
import com.example.MovieApp.ui.theme.SplashScreenTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue

class SplashingScreen : ComponentActivity() {
    // creating view model instance by factory design pattern
    val viewModel : MoviesViewModel by viewModels {
        MoviesViewModelFactory(
            repo = MoviesRepositoryImpl(
                remoteDataSource = RemoteDataSourceImpl()
            )
        )
    }
    // my api key ->  8375062ce126aac7379b665b2af3d0ed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreenTheme {
                // A surface container using the 'background' color from the theme
                SplashingScreen(viewModel )
            }
        }
    }
}

@Composable
fun SplashingScreen(
    viewModel: MoviesViewModel
) {
    // first i instantiate the ui state by collecting the state flow from view model
    val uiState by viewModel.popularMovies.collectAsStateWithLifecycle()
    // then i call the get popular movies function to fetch data from api which will make the data
    // ready for the user before entering the main screen
    // those popular movies will be stored in the database
    viewModel.getPopularMovies(1)
    // i made a box to make an overlay screen over the background image
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        // Background image
        Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.onboardingscreen2),
            contentDescription = "splash screen image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        var showButton by remember { mutableStateOf(false) }

        // When uiState is Success, launch a coroutine to delay showing the button
        LaunchedEffect(uiState) {
            if (uiState is UiState.Success) {
                kotlinx.coroutines.delay(2000) // delay 1 second
                showButton = true
            }
        }

        // Content overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // or Top if you want top
        ) {
            Text(
                text = "Movie Name",
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Discover and Explore Movies",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                color = Color.White
            )
            when{
                (uiState is UiState.Loading && showButton== false) || (uiState is UiState.Success && showButton==false) -> {
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
                uiState is UiState.Success && showButton -> {
                    Button(
                        onClick = { /* Navigate */ },
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
                uiState is UiState.Error  -> {
                    Text(text = "Error: ${(uiState as UiState.Error).message}")
                }
            }
        }
    }
}

// Preview function to visualize the SplashingScreen composable to see how it looks
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashingScreen(
        viewModel = MoviesViewModel(
            repo = MoviesRepositoryImpl(
                remoteDataSource = RemoteDataSourceImpl()
            )
        )
    )
}