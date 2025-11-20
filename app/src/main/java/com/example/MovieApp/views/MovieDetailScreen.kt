package com.example.movie_app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.MovieApp.dto.Movie
import com.example.MovieApp.viewModels.MoviesViewModel.MoviesViewModel
import com.example.movie_app.LightGrayText

//  **1. define colors & theme **

val DarkBackground = Color(0xFF101010)
val DarkCardBackground = Color(0xFF1E1E1E)
val GoldAccent = Color(0xFFF0C14B)
val RedPlay = Color(0xFFD50000)
val MaroonChip = Color(0xFF4A141C)
val LightGrayText = Color(0xFFB0B0B0)
val WightGrayText = Color(0xFFE7DADA)

//// **2. define data **
//data class Movie(
//    val title: String,
//    val synopsis: String,
//    val director: String,
//    val cast: List<String>,
//    val rating: String,
//    val year: String,
//    val duration: String,
//    val posterUrl: String, // URL for the small poster
//    val backgroundUrl: String // URL for the large header image
//)

//val sampleMovie = Movie(
//    title = "Crouching Tiger, Hidden Dragon",
//    synopsis = "A legendary martial arts master must retrieve a stolen sword and confront her past in this visually stunning epic.",
//    director = "Ang Lee",
//    cast = listOf("Chow Yun-fat", "Michelle Yeoh", "Zhang Ziyi"),
//    rating = "9.2",
//    year = "2000",
//    duration = "120",
//    posterUrl = "https://placehold.co/400x600/202020/F0C14B?text=Poster", // Placeholder
//    backgroundUrl = "https://placehold.co/600x400/503030/FFFFFF?text=Background" // Placeholder
//)

// ** 3. main screen composable**
/**
 *  Scaffold used to  place the top back button.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(viewModel: MoviesViewModel,navController: NavController) {
    // Scaffold is used to get a standard app layout.
    Scaffold(
        containerColor = DarkBackground,

    ) { paddingValues ->
        val movie by viewModel.selectedMovie.collectAsState()  // call the selected movie to the ui screen
        // and check if the movie is empty
        if (movie != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // padding from Scaffold
            ) {
                //  section: header

                item {
                    MovieHeader(movie = movie!!, navController = navController)
                }

                item {
                    ActionButtons()
                }

                item {
                    SynopsisSection(synopsis = movie!!.overview)
                }

                item {
                    DirectorSection(director = "Ang Lee")
                }

                item {
                    CastSection(cast = listOf("Chow Yun-fat", "Michelle Yeoh", "Zhang Ziyi"))
                }

                item {
                    InfoCardsSection(movie = movie!!)
                }

                //  spacer between bottom and cards section
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = GoldAccent)
            }
        }

    }
}

// ** 4.screen section composables**

/**
 * Displays the large background image, gradient, poster, title, and tags.
 */
@Composable
fun MovieHeader(movie: Movie, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp) // Fixed height for header area
    ) {

        // 1. Background Image
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}", // got from the object movie from api
            contentDescription = "Movie Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box( modifier = Modifier
        .size(50.dp)
        , // Darker yellow circle
        contentAlignment = Alignment.Center){
        IconButton(onClick = { navController.popBackStack()}) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }

        // 2. Gradient Overlay (from transparent to dark)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, DarkBackground),
                        startY = 300f // Start gradient lower
                    )
                )
        )

        // 3. Content (Poster, Info)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            // 3.1 Poster Image
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, GoldAccent),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) //  Horiz. space between poster and info.


            // 3.2 Info (Title, Tags, Duration)
            Column(verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp)) // Vertical space between title and tags
                Row(verticalAlignment = Alignment.CenterVertically) {
                    InfoTag(text = movie.vote_average.toString(), icon = Icons.Default.Star)
                    InfoTag(text = movie.release_date?.split("-")?.get(0) ?: "CommingSoon")
                    InfoTag(text = "Action")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "120 mins",
                    color = LightGrayText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

/**
 * Displays the "Play" button and watch later, favorite, and share icons.
 */
@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Play Button
        Button(
            onClick = { /* Handle Play */ },
            colors = ButtonDefaults.buttonColors(containerColor = RedPlay),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .weight(1f) // Takes up available space
                .height(48.dp)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Play", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Secondary Icon Buttons
        ActionButton(icon = Icons.Default.FavoriteBorder, description = "Favorite")
        ActionButton(icon = Icons.Default.AccessTime, description = "Watch Later")
        ActionButton(icon = Icons.Default.Share, description = "Share")
    }
}

/**
 * SECTION: SYNOPSIS
 */
@Composable
fun SynopsisSection(synopsis: String) {
    SectionCard(title = "Description") {
        Text(
            text = synopsis,
            color = WightGrayText,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 22.sp

        )
        Box(
                modifier = Modifier.border(1.dp, GoldAccent, RoundedCornerShape(12.dp))
        )

    }
}
/**
 * SECTION: DIRECTOR
 */
@Composable
fun DirectorSection(director: String) {
    SectionCard(title = "Director") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Movie,
                contentDescription = "Director",
                tint = GoldAccent,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF232121), CircleShape)//////////
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Director",
                    color = WightGrayText,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = director,
                    color = LightGrayText,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

/**
 * SECTION: CAST
 * Uses SectionCard and FlowRow to display chips.
 */
@OptIn(ExperimentalLayoutApi::class) // For FlowRow
@Composable
fun CastSection(cast: List<String>) {
//    val cardShape = RoundedCornerShape(16.dp)
//        Box(modifier = Modifier.border(1.dp, GoldAccent, cardShape)
//
//        ){
            SectionCard(title = "Cast") {
                // FlowRow automatically wraps items to the next line.
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    cast.forEach { name ->
                        CastChip(name = name)
                    }
                }


            }
        }


/**
 * SECTION: INFO CARDS (Release Year & Rating)
 */
@Composable
fun InfoCardsSection(movie: Movie) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                ,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallInfoCard(
                label = "Release Year",
                value = movie.release_date,
                icon = Icons.Default.CalendarToday,
                modifier = Modifier.weight(1f)
            )
            SmallInfoCard(
                label = "Rating",
                value = "${movie.vote_average}/10",
                icon = Icons.Default.StarBorder,
                modifier = Modifier.weight(1f)
            )

        }
}


// --- 5. reusable helper composables ---

/**
 * A small tag for info like rating, year, genre.
 */
@Composable
fun InfoTag(text: String, icon: ImageVector? = null) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = GoldAccent,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(
                text = text,
                color = GoldAccent,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
        }

}

/**
 * icon button (Heart, Clock, Share).
 */
@Composable
fun ActionButton(icon: ImageVector, description: String) {
    IconButton(
        onClick = { /* Handle action */ },
        modifier = Modifier
            .size(48.dp)
            .background(DarkCardBackground, CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.White
        )
    }
}

/**
 *  card container for sections like Synopsis, Director, Cast.
 */
@Composable
fun SectionCard(
    title: String,
    content: @Composable () -> Unit // Content is passed as a lambda
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFF232121), RoundedCornerShape(12.dp))
            .padding(16.dp)

    ) {
        // Title with yellow accent bar
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(20.dp)
                    .background(GoldAccent)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        content()
    }
}

/**
 *  card for displaying info like Release Year.
 */
@Composable
fun SmallInfoCard(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkCardBackground),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = label, color = LightGrayText, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = GoldAccent,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = value,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/**
 * A chip for displaying a cast member's name.
 */
@Composable
fun CastChip(name: String) {
    Box(
        modifier = Modifier
            .background(MaroonChip.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, MaroonChip), RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text = name, color = LightGrayText, style = MaterialTheme.typography.bodySmall)
    }
}
