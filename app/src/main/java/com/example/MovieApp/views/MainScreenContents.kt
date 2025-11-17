import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.MovieApp.viewModels.MoviesViewModel.MoviesViewModel
import com.example.MovieApp.viewModels.fake.FakeMoviesRepository
import com.example.MovieApp.views.CustomBottomNavBar
import com.example.MovieApp.views.FavoritesScreen
import com.example.MovieApp.views.HomeScreen
import com.example.MovieApp.views.WatchLaterScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MoviesViewModel) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomBottomNavBar(navController = navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeScreen(viewModel)
            }
            composable("favorites") {
                FavoritesScreen()
            }
            composable("watchlater") {
                WatchLaterScreen()
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