package com.niallmurph.jetweatherforecast.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.niallmurph.jetweatherforecast.widgets.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    var unitToggleState = remember { mutableStateOf(false) }
    var measurementUnits = listOf("Imperial (ºF)", "Celsius (ºC)")
    var choiceState = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Settings",
                icon = Icons.Default.ArrowBack,
                onMainScreen = false,
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change Units of Measurement",
                    modifier = Modifier.padding(12.dp)
                )

                IconToggleButton(
                    checked = !unitToggleState.value,
                    onCheckedChange = {
                        unitToggleState.value = !it
                        if (unitToggleState.value) {
                            choiceState.value = "Imperial (ºF)"
                        } else {
                            choiceState.value = "Celsius (ºC)"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .padding(6.dp)
                        .background(Color.Green.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = if(unitToggleState.value) "Fahrenheit ºF" else "Celsius ºC"
                    )
                }
            }
        }
    }
}