import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar() {

    val navBarBackgroundColor = Color(0xFF1A1A1A)
    val selectedContentColor = Color(0xFFE5C17B)
    val unselectedContentColor = Color(0xFFAAAAAA)

    var selectedIndex by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(navBarBackgroundColor)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val items = listOf("Home", "Favorite", "Watch Later")
        val chineseLabels = listOf("首页","收藏","稍后")
        items.forEachIndexed { index, label ->

            Button(
                onClick = { selectedIndex = index },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(0.dp)


            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "$label Button",
                        tint = if (selectedIndex == index) selectedContentColor else unselectedContentColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = label,
                        color = if (selectedIndex == index) selectedContentColor else unselectedContentColor
                    )
                    Text(
                        text = chineseLabels[index],
                        color = if (selectedIndex == index) selectedContentColor else unselectedContentColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar()
}
