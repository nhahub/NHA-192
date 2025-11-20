import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.MovieApp.viewModels.MoviesViewModel.MoviesViewModel
import com.example.MovieApp.views.ActionMovies
import com.example.MovieApp.views.AdventureMovies
import com.example.MovieApp.views.ComedyMovies
import com.example.MovieApp.views.CustomBottomNavBar
import com.example.MovieApp.views.FantasyMovies
import com.example.MovieApp.views.FavoritesScreen
import com.example.MovieApp.views.HomeScreen
import com.example.MovieApp.views.SearchScreen
import com.example.MovieApp.views.TopRatedMoviesScreen
import com.example.MovieApp.views.WatchLaterScreen
import com.example.movie_app.MovieDetailScreen

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
                HomeScreen(viewModel, navController=navController)
            }
            composable("favorites") {
                FavoritesScreen(viewModel = viewModel)
            }
            composable("watchlater") {
                WatchLaterScreen(viewModel = viewModel)
            }
            composable("Action") {
                ActionMovies(viewModel = viewModel, navController=navController)
            }
            composable("Adventure") {
                AdventureMovies(viewModel = viewModel,navController=navController)
            }
            composable("Comedy") {
                ComedyMovies(viewModel = viewModel, navController=navController)
            }
            composable("Fantasy") {
                FantasyMovies(viewModel = viewModel, navController=navController)
            }
            composable("TopRated") {
                TopRatedMoviesScreen(viewModel = viewModel, navController=navController)
            }
            composable("search") {
                SearchScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onMovieClick = { navController.navigate("movie_detail") }
                )
            }
            composable("movie_detail") {
                MovieDetailScreen()
            }
        }
    }
}