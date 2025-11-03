package com.example.MovieApp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MovieApp.ui.theme.Almond
import com.example.MovieApp.ui.theme.Grapefruit
import com.example.MovieApp.ui.theme.OldBrick
import com.example.MovieApp.ui.theme.Saffron
import com.example.MovieApp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(navController: NavController) {
    val oldPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

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
                            "设 Change Password",
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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Saffron
                )
                Spacer(modifier = Modifier.height(164.dp))
                OutlinedTextField(
                    value = oldPassword.value,
                    onValueChange = { oldPassword.value = it },
                    label = { Text("Old Password") },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = "Old Password",
                            tint = Color.LightGray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.White,
                        unfocusedContainerColor = Color.Black.copy(alpha = 0.5f),
                        focusedContainerColor = Color.Black.copy(alpha = 0.5f),
                        focusedBorderColor = Saffron,
                        unfocusedBorderColor = Saffron,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedLabelColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it },
                    label = { Text("New Password") },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = "New Password",
                            tint = Color.LightGray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.White,
                        unfocusedContainerColor = Color.Black.copy(alpha = 0.5f),
                        focusedContainerColor = Color.Black.copy(alpha = 0.5f),
                        focusedBorderColor = Saffron,
                        unfocusedBorderColor = Saffron,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedLabelColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirm New Password") },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = "Confirm New Password",
                            tint = Color.LightGray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = Color.White,
                        unfocusedContainerColor = Color.Black.copy(alpha = 0.5f),
                        focusedContainerColor = Color.Black.copy(alpha = 0.5f),
                        focusedBorderColor = Saffron,
                        unfocusedBorderColor = Saffron,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedLabelColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Grapefruit,
                        contentColor = Color.White,
                        disabledContentColor = Color.LightGray,
                        disabledContainerColor = OldBrick
                    ),
                ) {
                    Text(
                        "Submit",
                        style = Typography.titleMedium,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChangePasswordPreview() {
    ChangePasswordScreen (navController = NavController(context = LocalContext.current))
}