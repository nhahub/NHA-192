package com.example.MovieApp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController


data class BottomNavItem(
    val label: String,
    val icon: ImageVector
)

// 2. ده الـ Composable بتاع الـ Bottom Bar
@Composable
fun CustomBottomNavBar(modifier: Modifier = Modifier , navController: NavController) {

    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(
        BottomNavItem("首页", Icons.Default.Home),
        BottomNavItem("收藏", Icons.Default.Favorite),
        BottomNavItem("稍后", Icons.Default.Delete)
    )

    val Screen =listOf("home","favorites","watchlater")

    val selectedBorderBrush = Brush.linearGradient(
        listOf(Color(0xFFFDEB71), Color(0xFFF8D423))
    )
    val selectedBackgroundColor = Color(0xFF401C1C)
    val selectedContentColor = Color(0xFFE5C17B)
    val unselectedContentColor = Color(0xFFAAAAAA)
    val navBarBackgroundColor = Color(0xFF1A1A1A)


    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(navBarBackgroundColor)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        items.forEachIndexed { index, item ->

            val isSelected = (selectedIndex == index)

            val selectedModifier = if (isSelected) {
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(selectedBackgroundColor, RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        brush = selectedBorderBrush,
                        shape = RoundedCornerShape(12.dp)
                    )
            } else {
                Modifier
                    .clip(RoundedCornerShape(12.dp))
            }

            val contentColor = if (isSelected) selectedContentColor else unselectedContentColor


            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp,vertical = 8.dp)
                    .height(56.dp)
                    .then(selectedModifier)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(Screen[index])
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = contentColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.label,
                        color = contentColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}