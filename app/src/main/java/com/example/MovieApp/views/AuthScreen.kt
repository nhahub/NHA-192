package com.example.MovieApp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AuthScreen(
    navController: NavController
){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = com.example.MovieApp.R.drawable.onboardingscreen1),
            contentDescription = "Authentication Screen Background",
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
                text = "Welcome Back!",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "Please sign in or sign up to continue",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Row {
                Button(
                    onClick = {
                        navController.navigate("sign_in_screen")
                    },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF334455),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0x66FFFFFF)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 2.dp
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(text = "Sign In")
                }
                Button(
                    onClick = {
                        navController.navigate("sign_up_screen")
                    },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF334455),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0x66FFFFFF)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 2.dp
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(text = "Sign Up")
                }
            }

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthScreenPreview(){
    AuthScreen(navController = rememberNavController())
}
