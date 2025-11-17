import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.MovieApp.R
import com.example.MovieApp.Utils.UiState
import com.example.MovieApp.ui.theme.Almond
import com.example.MovieApp.ui.theme.OldBrick
import com.example.MovieApp.viewModels.MoviesViewModel.MoviesViewModel
import com.example.MovieApp.viewModels.fake.FakeMoviesRepository
import com.example.MovieApp.views.CustomBottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MoviesViewModel) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var searchText by remember { mutableStateOf("") }
    val popularMoviesState = viewModel.popularMovies.collectAsState().value
    val topRatedMoviesState = viewModel.TopRatedMovies.collectAsState().value
    val upComingMovies = viewModel.UpComingMovies.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getTopRatedMovies(1)
        viewModel.getPopularMovies(1)
        viewModel.getUpComingMovies(1)
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Navigation Drawer", modifier = Modifier.padding(16.dp))
            }
        }
    ) {

        Scaffold(
            bottomBar = {
                CustomBottomNavBar(modifier = Modifier.fillMaxWidth())
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {

                // ============================
                // HEADER WITH BACKGROUND IMAGE
                // ============================

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp) // ← IMPORTANT
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.appbarphoto),
                            contentDescription = "Background Image for the App Bar",
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
                            // --- Top Row (Drawer + Title) ---
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth() // take the full width of the parent
                                        .height(100.dp), // optional height
                                    contentAlignment = Alignment.CenterStart// centers content inside the Box
                                ) {
                                    Text(
                                        text = "春影 ChunCine",
                                        color = Color.Yellow,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 35.sp
                                    )
                                }
                            }

                            // --- Search bar ---
                            OutlinedTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
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
                                        color = Color(0xFFBDBDBD) // placeholder color is grey too
                                    )
                                },
                                textStyle = LocalTextStyle.current.copy(
                                    color = Color(0xFFE0E0E0) // The text color is Grey
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .background(
                                        color = Color(0xFF3A0000),
                                        shape = RoundedCornerShape(16.dp)
                                    ),
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = Color(0xFFFFD54F), // Yellow border
                                    focusedBorderColor = Color(0xFFFFEB3B),   // Yellow brighter
                                    cursorColor = Color(0xFFFFEB3B),          // Yellow cursor
                                )
                            )

                        }
                    }
                }

                // ============================
                // MAIN CONTENT
                // ============================
                item{
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .background(brush = Brush.linearGradient(
                                colorStops = arrayOf(
                                    0.0f to OldBrick.copy(alpha = 0.7f),
                                    0.5f to Almond.copy(alpha = 0.7f),
                                    1.0f to Color.Black.copy(alpha = 0.1f),
                                ),
                                start = Offset.Zero,
                                end = Offset.Infinite
                            ))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ){
                            // Header of the popular section
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "热门电影 Popular Movies",
                                    fontWeight = FontWeight.W400,
                                    fontSize = 20.sp,
                                    color = Color.Yellow,
                                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                                )
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    when (popularMoviesState) {
                                        is UiState.Loading -> {
                                            // ممكن تحط placeholder أو ProgressBar
                                            item {
                                                Text("Loading...", color = Color.White, modifier = Modifier.padding(16.dp))
                                            }
                                        }
                                        is UiState.Success -> {
                                            val movies = popularMoviesState.data
                                            items(movies) { movie ->
                                                MovieCard(
                                                    movie = movie,
                                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                                                )
                                            }
                                        }
                                        is UiState.Error -> {
                                            item {
                                                Text(
                                                    text = "Error: ${popularMoviesState.message}",
                                                    color = Color.Red,
                                                    modifier = Modifier.padding(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                            }

                            // Top Rated Movies
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "趋势影片 Top Rated Movies",
                                    fontWeight = FontWeight.W400,
                                    fontSize = 20.sp,
                                    color = Color.Yellow,
                                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                                )
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    when (topRatedMoviesState) {
                                        is UiState.Loading -> {
                                            // ممكن تحط placeholder أو ProgressBar
                                            item {
                                                Text("Loading...", color = Color.White, modifier = Modifier.padding(16.dp))
                                            }
                                        }
                                        is UiState.Success -> {
                                            val movies = topRatedMoviesState.data
                                            items(movies) { movie ->
                                                MovieCard(
                                                    movie = movie,
                                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                                                )
                                            }
                                        }
                                        is UiState.Error -> {
                                            item {
                                                Text(
                                                    text = "Error: ${topRatedMoviesState.message}",
                                                    color = Color.Red,
                                                    modifier = Modifier.padding(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                            }

                            // Upcoming Movies

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "即将上映 UpComing Movies",
                                    fontWeight = FontWeight.W900,
                                    fontSize = 20.sp,
                                    color = Color.Yellow,
                                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                                )
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp , bottom = 20.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    when (upComingMovies) {
                                        is UiState.Loading -> {
                                            // ممكن تحط placeholder أو ProgressBar
                                            item {
                                                Text("Loading...", color = Color.White, modifier = Modifier.padding(16.dp))
                                            }
                                        }
                                        is UiState.Success -> {
                                            val movies = upComingMovies.data
                                            items(movies) { movie ->
                                                MovieCard(
                                                    movie = movie,
                                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                                                )
                                            }
                                        }
                                        is UiState.Error -> {
                                            item {
                                                Text(
                                                    text = "Error: ${upComingMovies.message}",
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val fakeRepo = FakeMoviesRepository()
    val fakeViewModel = MoviesViewModel(fakeRepo)

    MainScreen(viewModel = fakeViewModel)
}