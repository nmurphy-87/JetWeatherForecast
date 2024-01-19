package com.niallmurph.jetweatherforecast.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.niallmurph.jetweatherforecast.R

@Composable
fun WeatherSplashScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .size(320.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(
            width = 2.dp,
            color = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sun icon image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(96.dp)
            )
            Text(
                "Find the sun",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray
            )
        }
    }
}