import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.MovieApp.Auth.EmailPasswordAuthManagerRepository
import com.example.MovieApp.Datastore.DataStoreManager
import com.example.MovieApp.Network.RemoteDataSourceImpl
import com.example.MovieApp.Repo.Search.SearchRepositoryImpl
import com.example.MovieApp.Repo.Settings.SettingsRepositoryImpl
import com.example.MovieApp.ViewModels.Auth.AuthViewModel
import com.example.MovieApp.ViewModels.Auth.AuthViewModelFactory
import com.example.MovieApp.ViewModels.Movies.MoviesViewModel
import com.example.MovieApp.ViewModels.Search.SearchViewModel
import com.example.MovieApp.ViewModels.Search.SearchViewModelFactory
import com.example.MovieApp.ViewModels.Settings.SettingsViewModel
import com.example.MovieApp.ViewModels.Settings.SettingsViewModelFactory
import com.example.MovieApp.Views.Components.CustomBottomNavBar
import com.example.MovieApp.Views.Screens.ActionMovies
import com.example.MovieApp.Views.Screens.AdventureMovies
import com.example.MovieApp.Views.Screens.ComedyMovies
import com.example.MovieApp.Views.Screens.FantasyMovies
import com.example.MovieApp.Views.Screens.FavoritesScreen
import com.example.MovieApp.Views.Screens.HomeScreen
import com.example.MovieApp.Views.Screens.SearchScreen
import com.example.MovieApp.Views.Screens.SettingsScreen
import com.example.MovieApp.Views.Screens.TopRatedMoviesScreen
import com.example.MovieApp.Views.Screens.UpcomingMovies
import com.example.MovieApp.Views.Screens.WatchLaterScreen
import com.example.movie_app.MovieDetailScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MoviesViewModel,
    parentNavController: NavController
) {

    val navController = rememberNavController()
    val context = LocalContext.current

    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(
            repo = EmailPasswordAuthManagerRepository()
        )
    )

    val searchViewModel: SearchViewModel = viewModel(
        factory = SearchViewModelFactory(
            repo = SearchRepositoryImpl(
                remoteDataSource = RemoteDataSourceImpl()
            )
        )
    )


    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(
            repository = SettingsRepositoryImpl(
                dataStoreManager = DataStoreManager(context)
            )
        )
    )

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
                HomeScreen(viewModel, navController = navController)
            }
            composable("favorites") {
                FavoritesScreen(viewModel = viewModel, navController = navController)
            }
            composable("watchlater") {
                WatchLaterScreen(viewModel = viewModel, navController = navController)
            }
            composable("Action") {
                ActionMovies(viewModel = viewModel, navController = navController)
            }
            composable("Adventure") {
                AdventureMovies(viewModel = viewModel, navController = navController)
            }
            composable("Comedy") {
                ComedyMovies(viewModel = viewModel, navController = navController)
            }
            composable("Fantasy") {
                FantasyMovies(viewModel = viewModel, navController = navController)
            }
            composable("TopRated") {
                TopRatedMoviesScreen(viewModel = viewModel, navController = navController)
            }
            composable("search") {
                SearchScreen(
                    searchViewModel = searchViewModel,
                    movieViewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    navController = navController,
                )
            }
            composable("MovieDetails") {
                MovieDetailScreen(
                    viewModel = viewModel, navController = navController
                )
            }
            composable("settings") {
                SettingsScreen(
                    authViewModel = authViewModel,
                    navController = navController,
                    settingsViewModel = settingsViewModel,
                    parentNavController = parentNavController
                )
            }
            composable("UpComing") {
                UpcomingMovies(viewModel = viewModel, navController = navController)
            }
        }
    }
}