package com.example.MovieApp.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.ui.theme.Saffron
import com.example.MovieApp.ui.theme.Typography

@Composable
fun SettingsButton(
    navController: NavController
) {
    Box(
        modifier = Modifier.padding(8.dp),
        contentAlignment = Alignment.Center
    ){
        Button(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 28.dp)
                .fillMaxWidth(),
            onClick = { navController.navigate("settings") },
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            // IMPROVEMENT: Use ButtonDefaults for cleaner code
            colors = ButtonDefaults.buttonColors(
                containerColor = Saffron,
                contentColor = Color.Black
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings",
                Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.padding(all = 2.dp),
                text = "Settings",
                style = Typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsButtonPreview() {
    SettingsButton(navController = NavController(LocalContext.current))
}