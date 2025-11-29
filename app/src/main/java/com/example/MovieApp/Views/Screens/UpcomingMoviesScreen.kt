package com.example.MovieApp.views

import MovieCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.R
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.viewModels.MoviesViewModel.MoviesViewModel
import kotlinx.coroutines.launch

@Composable
fun UpcomingMovies(viewModel: MoviesViewModel, navController: NavController){

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getFantasyMovies(1)
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets(0.dp)
            ) {
                DrawerContent(navController = navController)
            }
        }
    ) {
        val upcomingMoviesState = viewModel.UpComingMovies.collectAsState().value

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.movies_genres_bg_2),
                contentDescription = "Sign Up Screen Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp)
            ) {
                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { navController.navigate("search") }
                    ) {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedTextField(
                            value = searchText,
                            onValueChange = {},
                            enabled = false,
                            readOnly = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = Color(0xFFFFD54F)
                                )
                            },
                            placeholder = {
                                Text(
                                    "Search..",
                                    color = Color(0xFFBDBDBD)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                                .background(Color(0xFF3A0000), RoundedCornerShape(16.dp)),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledBorderColor = Color(0xFFFFD54F),
                                disabledTextColor = Color(0xFFE0E0E0),
                                cursorColor = Color(0xFFFFEB3B)

                            )
                        )
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        when (upcomingMoviesState) {
                            is UiState.Loading -> {
                                item {
                                    Text(
                                        "Loading...",
                                        color = Color.White,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }

                            is UiState.Success -> {
                                items(upcomingMoviesState.data) { movie ->
                                    MovieCard(movie = movie)
                                }
                            }

                            is UiState.Error -> {
                                item {
                                    Text(
                                        text = "Error: ${upcomingMoviesState.message}",
                                        color = Color.Red,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




