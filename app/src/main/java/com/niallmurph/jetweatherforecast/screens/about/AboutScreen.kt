package com.niallmurph.jetweatherforecast.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.niallmurph.jetweatherforecast.widgets.WeatherAppBar
import com.niallmurph.jetweatherforecast.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "About",
                icon = Icons.Default.ArrowBack,
                onMainScreen = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.about_app))
                Text(text = stringResource(id = R.string.app_name))
            }
        }
    }

}