import androidx.compose.foundation.border
import androidx.compose.foundation.clickable // Import clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.dto.Movie
import coil.compose.AsyncImage
import com.example.MovieApp.viewModels.MoviesViewModel.MoviesViewModel

// A Composable that represents a movie card in the UI
@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MoviesViewModel
) {

    // Define a gradient brush for the card border
    val cardBorderGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFDEB71),
            Color(0xFFF8D423)
        )
    )

    // Adjusting a round corner shape for the card
    val cardShape = RoundedCornerShape(16.dp)

    // A Column to hold the card content
    Column(
        modifier = modifier
            .width(170.dp)
            // 2. Apply the clickable modifier here.
            // We clip it to the cardShape so the ripple effect matches the rounded corners (optional but looks better)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = {
                viewModel.setSelectedMovie(movie)
                navController.navigate("MovieDetails")
            })
    ) {

        // A Box for the movie poster with a border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .border(
                    width = 3.dp, // width of the border
                    brush = cardBorderGradient,
                    shape = cardShape
                )
                .padding(4.dp)
                .clip(cardShape)
        ) {
            // Using Coil to load and display the movie poster
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // A Text for the movie title
        Text(
            text = movie.title,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))


        // A Row to hold the rating and release date
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // An Icon for the rating
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFC107), // Star color
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            // A Text for the rating
            Text(
                text = movie.vote_average.toString(),
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))

            // A Text for the release date
            Text(
                text = "(${movie.release_date})",
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}