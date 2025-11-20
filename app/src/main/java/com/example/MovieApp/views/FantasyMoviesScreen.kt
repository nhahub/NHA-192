package com.example.MovieApp.views

import MovieCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.R
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.viewModels.MoviesViewModel.MoviesViewModel

@Composable
fun FantasyMovies(viewModel: MoviesViewModel, navController: NavController){

    LaunchedEffect(Unit) {
        viewModel.getFantasyMovies(1)
    }

    val FantasyMoviesState = viewModel.FantasyMovies.collectAsState().value

    Box(){
        Image(
            painter = painterResource(id = R.drawable.movies_genres_bg_2),
            contentDescription = "Sign Up Screen Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)) // أسود شفاف (50%)
        )

        LazyVerticalGrid(

            columns = GridCells.Fixed(2),


            contentPadding = PaddingValues(8.dp),


            verticalArrangement = Arrangement.spacedBy(8.dp),


            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (FantasyMoviesState) {
                is UiState.Loading -> {
                    item {
                        Text("Loading...", color = Color.White, modifier = Modifier.padding(16.dp))
                    }
                }

                is UiState.Success -> {
                    items(FantasyMoviesState.data) { movie ->
                        MovieCard(movie = movie, navController = navController, viewModel = viewModel)
                    }
                }

                is UiState.Error -> {
                    item {
                        Text(
                            text = "Error: ${FantasyMoviesState.message}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}