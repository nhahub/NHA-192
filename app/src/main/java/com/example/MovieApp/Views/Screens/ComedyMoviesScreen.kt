package com.example.MovieApp.Views.Screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MovieApp.R
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.ViewModels.Movies.MoviesViewModel
import com.example.MovieApp.Views.Components.DrawerContent
import kotlinx.coroutines.launch
import com.example.MovieApp.ui.themes.customFont
@Composable
fun ComedyMovies(viewModel: MoviesViewModel, navController: NavController) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getComedyMovies(1)
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
        val ComedyMoviesState = viewModel.ComedyMovies.collectAsState().value

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                IconButton(
                    onClick = { scope.launch { drawerState.open() } },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Comedy Movies",
                    color = Color(0xFFFFD54F),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = customFont,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Box(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { navController.navigate("search") }
            ) {
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
                    modifier = Modifier
                        .fillMaxWidth()
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
                when (ComedyMoviesState) {
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
                        items(ComedyMoviesState.data) { movie ->
                            MovieCard(
                                movie = movie,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }

                    is UiState.Error -> {
                        item {
                            Text(
                                text = "Error: ${ComedyMoviesState.message}",
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