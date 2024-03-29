package com.niallmurph.jetweatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.niallmurph.jetweatherforecast.data.DataOrException
import com.niallmurph.jetweatherforecast.model.Weather
import com.niallmurph.jetweatherforecast.model.WeatherItem
import com.niallmurph.jetweatherforecast.navigation.WeatherScreens
import com.niallmurph.jetweatherforecast.screens.settings.SettingsViewModel
import com.niallmurph.jetweatherforecast.utils.Constants.BASE_IMAGE_URL
import com.niallmurph.jetweatherforecast.utils.formatDecimals
import com.niallmurph.jetweatherforecast.utils.formateDate
import com.niallmurph.jetweatherforecast.widgets.*

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    settingsViewModel : SettingsViewModel = hiltViewModel(),
    city : String?) {

    val currentCity : String = if(city!!.isBlank()) "Belfast" else city

    Log.d("Main Screen", "Message : $city")
    val unitFromDb = settingsViewModel.unitList.collectAsState().value
    var unit = remember { mutableStateOf("imperial") }
    var isImperial = remember { mutableStateOf(false) }

    if(!unitFromDb.isNullOrEmpty()) {

        unit.value = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial.value = unit.value == "imperial"

        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)
        ) {
            value = viewModel.getWeatherData(city = currentCity, units = unit.value)
        }.value

        if (weatherData.loading == true) {
            CircularProgressIndicator()
        } else if (weatherData.data != null) {
            MainScaffold(weather = weatherData.data!!, navController = navController, isImperial = isImperial.value)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController, isImperial: Boolean) {

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "${weather.city.name}, ${weather.city.country}",
                navController = navController,
                onAddActionClicked = {
                     navController.navigate(WeatherScreens.SearchScreen.name)
                },
                elevation = 4.dp
            ) {
            }
        }
    ) {
        MainContent(data = weather, isImperial = isImperial)
    }

}

@Composable
fun MainContent(data: Weather, isImperial: Boolean) {

    val imageUrl = BASE_IMAGE_URL + "${data.list[0].weather[0].icon}.png"
    val weatherItem = data.list[0]

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formateDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Image
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = weatherItem.weather[0].description, fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(data = weatherItem, isImperial = isImperial)
        Divider()
        SunriseSunsetRow(data = weatherItem)
        Text(
            text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)
                }
            }
        }
    }
}


