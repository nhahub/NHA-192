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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// --- 1. DEFINE COLORS & THEME ---
// Based on your images, we'll define a dark theme palette.
val DarkBackground = Color(0xFF101010)
val DarkCardBackground = Color(0xFF1E1E1E) // Slightly lighter for cards
val GoldAccent = Color(0xFFF0C14B)
val RedPlay = Color(0xFFD50000)
val MaroonChip = Color(0xFF4A141C) // Background for cast chips
val LightGrayText = Color(0xFFB0B0B0)

// --- 2. DEFINE DUMMY DATA ---
// In a real app, this would come from a ViewModel or API.
data class Movie(
    val title: String,
    val synopsis: String,
    val director: String,
    val cast: List<String>,
    val rating: String,
    val year: String,
    val duration: String,
    val posterUrl: String, // URL for the small poster
    val backgroundUrl: String // URL for the large header image
)

val sampleMovie = Movie(
    title = "Crouching Tiger, Hidden Dragon",
    synopsis = "A legendary martial arts master must retrieve a stolen sword and confront her past in this visually stunning epic.",
    director = "Ang Lee",
    cast = listOf("Chow Yun-fat", "Michelle Yeoh", "Zhang Ziyi"),
    rating = "9.2",
    year = "2000",
    duration = "120",
    posterUrl = "https://placehold.co/400x600/202020/F0C14B?text=Poster", // Placeholder
    backgroundUrl = "https://placehold.co/600x400/503030/FFFFFF?text=Background" // Placeholder
)

// --- 3. MAIN SCREEN COMPOSABLE ---
/**
 * This is the main composable for the entire screen.
 * It uses a Scaffold to easily place the top back button.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen() {
    // We use a Scaffold to get a standard app layout.
    // The topBar is transparent to float over the header.
    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { /* No title */ },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back press */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        // LazyColumn is used for a scrollable screen.
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from Scaffold
        ) {
            // --- SECTION: HEADER ---
            // The header is the first item in the list.
            item {
                MovieHeader(movie = sampleMovie)
            }

            // --- SECTION: ACTION BUTTONS ---
            item {
                ActionButtons()
            }

            // --- SECTION: SYNOPSIS ---
            item {
                SynopsisSection(synopsis = sampleMovie.synopsis)
            }

            // --- SECTION: DIRECTOR ---
            item {
                DirectorSection(director = sampleMovie.director)
            }

            // --- SECTION: CAST ---
            item {
                CastSection(cast = sampleMovie.cast)
            }

            // --- SECTION: INFO CARDS (Release & Rating) ---
            item {
                InfoCardsSection(movie = sampleMovie)
            }

            // Add some bottom padding
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// --- 4. SCREEN SECTION COMPOSABLES ---

/**
 * SECTION: HEADER
 * Displays the large background image, gradient, poster, title, and tags.
 */
@Composable
fun MovieHeader(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp) // Fixed height for the header area
    ) {
        // 1. Background Image
        AsyncImage(
            model = movie.backgroundUrl,
            contentDescription = "Movie Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

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

        // 3. Content (Poster, Title, Info)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            // Poster Image
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, GoldAccent),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Title, Tags, Duration
            Column(verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    InfoTag(text = movie.rating, icon = Icons.Default.Star)
                    InfoTag(text = movie.year)
                    InfoTag(text = "Action") // Hardcoded from image
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${movie.duration} min",
                    color = LightGrayText,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

/**
 * SECTION: ACTION BUTTONS
 * Displays the "Play" button and other icon buttons.
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
        // Play Button (Primary)
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
 * Uses the reusable SectionCard composable.
 */
@Composable
fun SynopsisSection(synopsis: String) {
    SectionCard(title = "Synopsis") {
        Text(
            text = synopsis,
            color = LightGrayText,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 22.sp
        )
    }
}

/**
 * SECTION: DIRECTOR
 * Also uses the reusable SectionCard.
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
                    .background(DarkCardBackground, CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Director",
                    color = LightGrayText,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = director,
                    color = Color.White,
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
 * A Row of two SmallInfoCard composables.
 */
@Composable
fun InfoCardsSection(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SmallInfoCard(
            label = "Release Year",
            value = movie.year,
            icon = Icons.Default.CalendarToday,
            modifier = Modifier.weight(1f)
        )
        SmallInfoCard(
            label = "Rating",
            value = "${movie.rating}/10",
            icon = Icons.Default.StarBorder,
            modifier = Modifier.weight(1f)
        )
    }
}


// --- 5. REUSABLE HELPER COMPOSABLES ---
// These helpers keep the code clean and readable (DRY principle).

/**
 * A small tag for info like rating, year, genre.
 */
@Composable
fun InfoTag(text: String, icon: ImageVector? = null) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp) // Space between tags
            .background(Color.DarkGray.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
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
}

/**
 * A single circular icon button (Heart, Clock, Share).
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
 * A reusable card container for sections like Synopsis, Director, Cast.
 * It provides the title with the yellow accent bar.
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
            .background(DarkCardBackground, RoundedCornerShape(12.dp))
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
 * A small card for displaying a single piece of info, like Release Year.
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
