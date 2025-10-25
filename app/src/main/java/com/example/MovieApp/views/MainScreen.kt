package com.example.MovieApp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun mainScreen(){
    Box(
        modifier = Modifier.padding(8.dp),
        contentAlignment = Alignment.Center
    ){
        Text("Hello Welcome to the main screen")
    }
}