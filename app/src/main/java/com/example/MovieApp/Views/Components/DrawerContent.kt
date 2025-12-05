package com.example.MovieApp.Views.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.ui.themes.OldBrick
import com.example.MovieApp.ui.themes.Saffron

@Composable
fun DrawerContent(navController: NavController) {

    ModalDrawerSheet(
        drawerContainerColor = Color.White,
        windowInsets = WindowInsets(0.dp),
        drawerShape = RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ===  (Header + Items) ===
            Column {
                // Header
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = OldBrick)
                        .padding(top = 36.dp, bottom = 24.dp, start = 24.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Account",
                        modifier = Modifier
                            .size(76.dp)
                            .border(
                                width = 2.dp,
                                color = Saffron,
                                shape = RoundedCornerShape(100)
                            ),
                        tint = Color.White,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            "李明華",
                            fontWeight = FontWeight.W900,
                            color = Color.White
                        )
                        Text(
                            "@liminghua",
                            fontWeight = FontWeight.W500,
                            color = Saffron
                        )
                    }
                }

                Spacer(Modifier.size(30.dp))

                // Action with genre id 28
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    label = { Text("Action Movies") },
                    selected = false,
                    icon = {

                        Icon(Icons.Filled.Movie, contentDescription = null)
                    },
                    onClick = { navController.navigate("Action") }
                )

                // Adventure with genre id 12
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 12.dp), // تظبيط المسافات
                    label = { Text("Adventure Movies") },
                    selected = false,
                    icon = {
                        // (4) استخدمنا Filled.Call بعد ما عملنا Import
                        Icon(Icons.Filled.Movie, contentDescription = null)
                    },
                    onClick = { navController.navigate("Adventure") }
                )

                // Comedy with genre id 35
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 12.dp), // تظبيط المسافات
                    label = {
                        Text(
                            text = "Comedy Movies"
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(Icons.Filled.Movie, contentDescription = null)
                    },
                    onClick = { navController.navigate("Comedy") }
                )

                // Fantasy with genre id 14
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 12.dp), // تظبيط المسافات
                    label = { Text("Fantasy Movies") },
                    selected = false,
                    icon = {
                        Icon(Icons.Filled.Movie, contentDescription = null)
                    },
                    onClick = { navController.navigate("Fantasy") }
                )

                // TopRated Movies in drawer
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    label = { Text("TopRated Movies") },
                    selected = false,
                    icon = {

                        Icon(Icons.Filled.Movie, contentDescription = null)
                    },
                    onClick = { navController.navigate("TopRated") }
                )

                // UpComing Movies in drawer
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    label = { Text("UpComing Movies") },
                    selected = false,
                    icon = {

                        Icon(Icons.Filled.Movie, contentDescription = null)
                    },
                    onClick = { navController.navigate("UpComing") }
                )


            }


            // === (Settings Button) ===
            Button(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 28.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate("Settings")
                },
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Saffron,
                    contentColor = Color.Black
                )
            ) {

                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier.padding(all = 2.dp),
                    text = "Settings",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}