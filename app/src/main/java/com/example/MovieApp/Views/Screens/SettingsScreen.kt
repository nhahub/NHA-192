package com.example.MovieApp.Views.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.R
import com.example.MovieApp.ViewModels.Auth.AuthViewModel
import com.example.MovieApp.ViewModels.Settings.SettingsViewModel
import com.example.MovieApp.Views.Components.ChangePasswordDialog
import com.example.MovieApp.ui.themes.Almond
import com.example.MovieApp.ui.themes.Cherrywood
import com.example.MovieApp.ui.themes.Grapefruit
import com.example.MovieApp.ui.themes.OldBrick
import com.example.MovieApp.ui.themes.Saffron
import com.example.MovieApp.ui.themes.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    parentNavController: NavController,
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    authViewModel: AuthViewModel
) {
    val darkMode by settingsViewModel.darkMode.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val openAlertDialog = remember { mutableStateOf(false) }
    val openSignOutDialog = remember { mutableStateOf(false) }

    if (openAlertDialog.value) {
        ChangePasswordDialog(
            onDismissRequest = { openAlertDialog.value = false },
            authViewModel = authViewModel
        )
    }

    if (openSignOutDialog.value) {
        SignOutConfirmationDialog(
            onDismissRequest = { openSignOutDialog.value = false },
            onConfirmSignOut = {
                authViewModel.signOut()
                parentNavController.navigate("splash_screen") {
                    popUpTo(0) { inclusive = true }
                }
            }
        )
    }

    Box {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Black.copy(alpha = 0.9f),
                            0.5f to Almond,
                            1.0f to Color.Black.copy(alpha = 0.9f),
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    modifier = Modifier.height(100.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Grapefruit,
                        scrolledContainerColor = Grapefruit,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text(
                            "设置 Settings",
                            modifier = Modifier.padding(vertical = 12.dp),
                            style = Typography.headlineMedium
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 24.dp)
                                .size(20.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Saffron
                )
                Spacer(modifier = Modifier.height(28.dp))
                ButtonSettingsCard(
                    title = "Change Password",
                    description = "Update your account password",
                    icon = Icons.Outlined.Lock,
                    buttonText = "Change Password",
                    onButtonClick = { openAlertDialog.value = true }
                )
//                Spacer(modifier = Modifier.height(28.dp))
//                SwitchSettingsCard(
//                    title = "Dark Mode",
//                    description = "Toggle dark theme\nappearance",
//                    icon = R.drawable.dark,
//                    onCheckedChange = { enabled ->
//                        coroutineScope.launch { settingsViewModel.setDarkMode(enabled = enabled) }
//                    },
//                    checked = darkMode
//                )
//                Spacer(modifier = Modifier.height(28.dp))
//                ButtonSettingsCard(
//                    title = "Profile Picture",
//                    description = "Update your avatar image",
//                    icon = Icons.Outlined.Person,
//                    buttonText = "Change Picture",
//                    onButtonClick = {}
//                )
                Spacer(modifier = Modifier.height(28.dp))
                ButtonSettingsCard (
                    title = "Sign out",
                    description = "Log out of your account",
                    icon = Icons.AutoMirrored.Outlined.ExitToApp,
                    buttonText = "Sign out",
                    onButtonClick = {
                        openSignOutDialog.value = true
                    }
                )
                Spacer(modifier = Modifier.height(28.dp))
                AboutCard()
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Composable
fun ButtonSettingsCard(
    title: String,
    description: String,
    icon: ImageVector,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 28.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    color = Saffron,
                    shape = RoundedCornerShape(16.dp),
                    width = 1.dp,
                )
                .padding(all = 24.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    Modifier
                        .size(52.dp)
                        .background(
                            color = Cherrywood,
                            shape = RoundedCornerShape(100)
                        )
                        .padding(10.dp),
                    tint = Saffron,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        title,
                        style = Typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        description,
                        style = Typography.titleSmall,
                        fontWeight = FontWeight.W500,
                        color = Color.LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grapefruit,
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = OldBrick
                ),
            ) {
                Text(buttonText, style = Typography.titleMedium)
            }
        }
    }
}

@Composable
fun SwitchSettingsCard(
    title: String,
    description: String,
    icon: Int,
    onCheckedChange: (Boolean) -> Unit,
    checked: Boolean
) {
    Column(modifier = Modifier.padding(horizontal = 28.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    color = Saffron,
                    shape = RoundedCornerShape(16.dp),
                    width = 1.dp,
                )
                .padding(all = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    Modifier
                        .size(52.dp)
                        .background(
                            color = Cherrywood,
                            shape = RoundedCornerShape(100)
                        )
                        .padding(10.dp),
                    tint = Saffron,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        title,
                        style = Typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        description,
                        style = Typography.titleSmall,
                        fontWeight = FontWeight.W500,
                        color = Color.LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Color.Black,
                    uncheckedBorderColor = Color.Transparent
                ),
            )
        }
    }
}

@Composable
fun AboutCard() {
    Column(modifier = Modifier.padding(horizontal = 28.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    color = Saffron,
                    shape = RoundedCornerShape(16.dp),
                    width = 1.dp,
                )
                .padding(all = 24.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    Modifier
                        .size(52.dp)
                        .background(
                            color = Cherrywood,
                            shape = RoundedCornerShape(100)
                        )
                        .padding(10.dp),
                    tint = Saffron,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        "About ChunCine",
                        style = Typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        "App information and credits",
                        style = Typography.titleSmall,
                        fontWeight = FontWeight.W500,
                        color = Color.LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Version",
                    style = Typography.titleSmall,
                    fontWeight = FontWeight.W500,
                    color = Color.LightGray
                )
                Text(
                    "1.0.0",
                    style = Typography.titleSmall,
                    color = Saffron
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Build",
                    style = Typography.titleSmall,
                    fontWeight = FontWeight.W500,
                    color = Color.LightGray
                )
                Text(
                    "2025.11.03",
                    style = Typography.titleSmall,
                    color = Saffron
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Saffron
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "春影 ChunCine - A beautiful movie discovery app inspired by traditional Chinese aesthetics. Experience cinema with elegance and cultural heritage.",
                style = Typography.titleSmall,
                fontWeight = FontWeight.W500,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                "© 2025 ChunCine. All rights reserved.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.titleSmall,
                fontWeight = FontWeight.W500,
                color = Color.White
            )
        }
    }
}

@Composable
fun SignOutConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmSignOut: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                "Sign Out",
                style = Typography.headlineSmall,
                color = Color.White
            )
        },
        text = {
            Text(
                "Are you sure you want to sign out? You'll need to log in again to access your account.",
                style = Typography.bodyMedium,
                color = Color.LightGray
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirmSignOut,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Saffron,
                    contentColor = Color.Black
                )
            ) {
                Text("Sign Out", style = Typography.titleMedium)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Saffron
                ),
                border = BorderStroke(1.dp, Saffron)
            ) {
                Text("Cancel", style = Typography.titleMedium)
            }
        },
        containerColor = Color.Black.copy(alpha = 0.9f),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 8.dp
    )
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SettingsPreview() {
//    SettingsScreen(navController = NavController(LocalContext.current))
//}