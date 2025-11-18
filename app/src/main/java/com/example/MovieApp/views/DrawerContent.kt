package com.example.MovieApp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call // (1) Import Call
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.Settings // (2) Import Settings Outlined
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.ui.theme.OldBrick
import com.example.MovieApp.ui.theme.Saffron

@Composable
fun DrawerContent(navController: NavController) {
    // (3) لازم تغلف كل حاجة بـ ModalDrawerSheet عشان ده الـ Container الرسمي للـ Drawer
    ModalDrawerSheet(
        drawerContainerColor = Color.White, // لون خلفية الـ Drawer
        windowInsets = WindowInsets(0.dp),
        drawerShape = RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp) // لو عايز تلغي الكيرف
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween, // عشان يزق الزرار تحت خالص
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // === الجزء العلوي (Header + Items) ===
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
                        imageVector = Icons.Filled.AccountCircle, // استخدم Filled بدل Default
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
                    modifier = Modifier.padding(horizontal = 12.dp), // تظبيط المسافات
                    label = { Text("Action Movies") },
                    selected = false,
                    icon = {
                        // (4) استخدمنا Filled.Call بعد ما عملنا Import
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
                    onClick = { navController.navigate("Adventure")}
                )

                // Comedy with genre id 35
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 12.dp), // تظبيط المسافات
                    label = {
                        Text(
                        text = "Comedy Movies"
                    ) },
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
                        // (4) استخدمنا Filled.Call بعد ما عملنا Import
                        Icon(Icons.Filled.Movie, contentDescription = null)
                    },
                    onClick = { navController.navigate("Fantasy") }
                )
            }

            // === الجزء السفلي (Settings Button) ===
            Button(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 28.dp)
                    .fillMaxWidth(),
                onClick = { /* Handle settings click */ },
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Saffron,
                    contentColor = Color.Black
                )
            ) {
                // (5) استخدمنا Outlined.Settings بعد الـ Import
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