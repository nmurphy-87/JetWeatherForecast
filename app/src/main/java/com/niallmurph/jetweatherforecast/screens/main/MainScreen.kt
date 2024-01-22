package com.niallmurph.jetweatherforecast.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.niallmurph.jetweatherforecast.data.DataOrException
import com.niallmurph.jetweatherforecast.model.Weather
import com.niallmurph.jetweatherforecast.screens.main.MainViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {

    ShowData(mainViewModel = viewModel)
}

@Composable
fun ShowData(mainViewModel: MainViewModel) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = "dublin")
    }.value

    if(weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        Text("Main Screen: ${weatherData.data.toString()}")
    }

}