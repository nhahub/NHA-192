package com.example.MovieApp.Views.Screens


import MovieCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MovieApp.Dto.Movie
import com.example.MovieApp.R
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.ViewModels.Movies.MoviesViewModel
import com.example.MovieApp.Views.Components.DrawerContent
import com.example.MovieApp.ui.themes.Almond
import com.example.MovieApp.ui.themes.OldBrick
import kotlinx.coroutines.launch
import com.example.MovieApp.ui.themes.customFont

@Composable
fun HomeScreen(
    viewModel: MoviesViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // Search state
    var searchText by remember { mutableStateOf("") }

    // Movie states
    val popularMoviesState = viewModel.popularMovies.collectAsState().value
    val topRatedMoviesState = viewModel.TopRatedMovies.collectAsState().value
    val upComingMoviesState = viewModel.UpComingMovies.collectAsState().value


    // Load data when screen opens
    LaunchedEffect(Unit) {
        viewModel.getTopRatedMovies(1)
        viewModel.getPopularMovies(1)
        viewModel.getUpComingMovies(1)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets(0.dp)
            ) {
                // THe Shape of the Nav Drawer is Here
                DrawerContent(navController = navController)
            }
        }
    ) {

        LazyColumn(
            modifier = modifier
        ) {

            // ============================
            // HEADER WITH BACKGROUND IMAGE
            // ============================
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.appbarphoto),
                        contentDescription = "Background Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x55FF0000))
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 30.dp, start = 10.dp)
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth() // take the full width of the parent
                                    .height(100.dp), // optional height
                                contentAlignment = Alignment.CenterStart// centers content inside the Box
                            ) {
                                Text(
                                    text = "德拉戈·布雷兹 DragoBlaze",
                                    color = Color.Yellow,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.W400,
                                    fontSize = 35.sp,
                                    fontFamily = customFont
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    navController.navigate("search")
                                }
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
                                        "搜索电影... Search movies...",
                                        color = Color(0xFFBDBDBD),
                                        fontFamily = customFont
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

                    }
                }
            }

            // ============================
            // MAIN CONTENT
            // ============================
            item {
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .background(
                            brush = Brush.linearGradient(
                                listOf(
                                    OldBrick.copy(alpha = 0.7f),
                                    Almond.copy(alpha = 0.7f),
                                    Color.Black.copy(alpha = 0.1f)
                                )
                            )
                        )
                ) {
                    Column {

                        // Popular Movies
                        SectionMovies(
                            title = "热门电影 • Popular Movies",
                            state = popularMoviesState,
                            navController = navController,
                            viewModel = viewModel
                        )

                        // Top Rated
                        SectionMovies(
                            title = "趋势影片 • Top Rated Movies",
                            state = topRatedMoviesState,
                            navController = navController,
                            viewModel = viewModel
                        )

                        // Upcoming
                        SectionMovies(
                            title = "即将上映 • UpComing Movies",
                            state = upComingMoviesState,
                            navController = navController,
                            viewModel = viewModel
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun SectionMovies(
    title: String,
    state: UiState<List<Movie>>,
    navController: NavController,
    viewModel: MoviesViewModel
) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = title,
            fontWeight = FontWeight.W400,
            fontSize = 20.sp,
            color = Color.Yellow,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            fontFamily = customFont
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (state) {
                is UiState.Loading -> {
                    item {
                        Text("Loading...", color = Color.White, modifier = Modifier.padding(16.dp))
                    }
                }

                is UiState.Success -> {
                    items(state.data) { movie ->
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
                            text = "Error: ${state.message}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}