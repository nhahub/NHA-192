package com.example.MovieApp.Views.Screens

import MovieCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MovieApp.R
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.ViewModels.Movies.MoviesViewModel
import com.example.MovieApp.ViewModels.Search.SearchViewModel


@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    movieViewModel: MoviesViewModel,
    onBack: () -> Unit,
    navController: NavController
) {

    val customFont = FontFamily(
        Font(R.font.spellofasia)
    )

    // --------Search input state from ViewModel---------
    val searchText by searchViewModel.query.collectAsState()

    // Collect merged and filtered movies from the view model
    val searchResultsState by searchViewModel.searchedMovie.collectAsState()

        val pop = movieViewModel.popularMovies

    Box(modifier = Modifier.fillMaxSize()) {

        // --------------background image for movie cards------------
        Image(
            painter = painterResource(id = R.drawable.movies_genres_bg_2),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        )

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // Back button and Search bar row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Search bar
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchViewModel.onSearchTextChange(it) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.Yellow)
                    },
                    placeholder = { Text("Search movies...", color = Color.LightGray) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Yellow,
                        focusedBorderColor = Color.Yellow,
                        cursorColor = Color.Yellow,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        unfocusedLeadingIconColor = Color.Yellow,
                        focusedLeadingIconColor = Color.Yellow
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            // Show "Popular movies..." text when entering
            if (searchText.isBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Search Results ...",
                    color = Color.Yellow,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp),
                    fontFamily = customFont
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Movies Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.weight(1f) // Critical for scrolling inside Column
            ) {
                when (val state = searchResultsState) {
                    is UiState.Loading -> item {
                        Text(text = "No searched movies yet.", color = Color.White, modifier = Modifier.padding(16.dp))
                    }
                    is UiState.Success -> {
                        val movies = state.data
                        if (movies.isEmpty()) {
                            item {
                                Text("No movies found", color = Color.White, modifier = Modifier.padding(16.dp))
                            }
                        } else {
                            items(movies) { movie ->
                                    MovieCard(movie = movie, navController = navController , viewModel = movieViewModel)
                            }
                        }
                    }
                    is UiState.Error -> item {
                        Text("Error: ${state.message}", color = Color.Red, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}


