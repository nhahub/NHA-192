package com.example.MovieApp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.MovieApp.R
import com.example.MovieApp.auth.AuthResult
import com.example.MovieApp.viewModels.AuthViewModel
import kotlinx.coroutines.delay


// sign up screen composable function
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    navController: NavController
) {

    val signUpstate by authViewModel.signupState.collectAsStateWithLifecycle()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    var pressed by remember { mutableStateOf(false) }

    LaunchedEffect(signUpstate) {
        if(signUpstate is AuthResult.Success<String>){
            delay(1000)
            navController.popBackStack()
            navController.navigate("main_screen") {
                popUpTo("sign_in_screen") { inclusive = true }
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
                .background(Color(0x80000000)) // أسود شفاف (50%)
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
            // name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name", color = Color.White) },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                )
            )
            // email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.White) },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                )
            )
            // password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                )
            )
            // phone number
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number", color = Color.White) },
                modifier = Modifier.padding(bottom = 24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White
                )
            )
            Button(
                onClick = { authViewModel.SignUp(email, password, name, phoneNumber) },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF334455),
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color(0x66FFFFFF)),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 2.dp
                )
            ) {
                if(pressed){
                    when(signUpstate){
                        is AuthResult.Success<String> -> {
                            Text(
                                text = "Signed Up successfully",
                                color = Color.Green
                            )
                        }
                        is AuthResult.Error ->{
                            Text(
                                text = (signUpstate as AuthResult.Error).message + "Try again",
                                color = Color.Red
                            )
                        }
                        is AuthResult.Loading ->{
                            Text(
                                text = "Signing You Up..."
                            )
                        }
                    }
                }else{
                    Text("Sign up")
                }
            }
        }
    }
}
