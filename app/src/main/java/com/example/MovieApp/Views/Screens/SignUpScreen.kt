package com.example.MovieApp.Views.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.MovieApp.R
import com.example.MovieApp.Auth.AuthResult
import com.example.MovieApp.ViewModels.Auth.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    navController: NavController
) {

    val signUpstate by authViewModel.signupState.collectAsStateWithLifecycle()

    // Getting values from ViewModel to ensure state is updated
    val email by authViewModel.email.collectAsStateWithLifecycle()
    val password by authViewModel.password.collectAsStateWithLifecycle()
    val name by authViewModel.name.collectAsStateWithLifecycle()
    val phoneNumber by authViewModel.phoneNumber.collectAsStateWithLifecycle()

    // Navigating on Success
    LaunchedEffect(signUpstate) {
        if(signUpstate is AuthResult.Success<String>){
            delay(1000)
            navController.popBackStack()
            navController.navigate("main_screen") {
                popUpTo("sign_up_screen") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.onboardingscreen1),
            contentDescription = "Sign Up Screen Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Create Account",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            // Name
            OutlinedTextField(
                value = name,
                onValueChange = { authViewModel.onNameChange(it) },
                label = { Text("Name", color = Color.White) },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { authViewModel.onEmailChange(it) },
                label = { Text("Email", color = Color.White) },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { authViewModel.onPasswordChange(it) },
                label = { Text("Password", color = Color.White) },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
            // Phone Number
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { authViewModel.onPhoneNumberChange(it) },
                label = { Text("Phone Number", color = Color.White) },
                modifier = Modifier.padding(bottom = 24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            // Row for Buttons (Back + Sign Up)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // 1. Back Button
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0x66FFFFFF)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text("Back")
                }

                // 2. Sign Up Button
                Button(
                    onClick = {
                        authViewModel.SignUp(email, password, name, phoneNumber)
                    },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD2B48C),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0x66FFFFFF)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 2.dp
                    ),
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text("Sign up")
                }
            }

            // Error Message Display
            if (signUpstate is AuthResult.Error) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Text(
                    text = (signUpstate as AuthResult.Error).message + "\nTry again",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color(0x33000000), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }

            // Success Message
            if (signUpstate is AuthResult.Success<String>) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Text(
                    text = "Account Created Successfully!",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}