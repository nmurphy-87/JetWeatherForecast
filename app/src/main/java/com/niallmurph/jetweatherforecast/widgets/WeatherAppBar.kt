package com.niallmurph.jetweatherforecast.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.niallmurph.jetweatherforecast.model.entities.Favourite
import com.niallmurph.jetweatherforecast.navigation.WeatherScreens
import com.niallmurph.jetweatherforecast.screens.favourite.FavouriteViewModel

@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    onMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        ShowSettingsDropDown(showDialog = showDialog, navController = navController)
    }

    val showIt = remember { mutableStateOf(false) }
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        },
        actions = {
            if (onMainScreen) {
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                }
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Icon")
                }
            } else Box {}
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon, contentDescription = null,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    },
                    tint = MaterialTheme.colors.onSecondary,
                )
            }
            if (onMainScreen) {

                val isAlreadyFavourite = favouriteViewModel
                    .favList.collectAsState().value.filter { item ->
                        (item.city == title.split(",")[0])
                    }

                if(isAlreadyFavourite.isNullOrEmpty()){
                    Icon(
                        imageVector = Icons.Default.Favorite, contentDescription = "Favourite Icon",
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .scale(0.9f)
                            .clickable {
                                val dataList = title
                                    .split(", ")
                                favouriteViewModel
                                    .insertFavourite(
                                        Favourite(
                                            city = dataList[0],
                                            country = dataList[1]
                                        )
                                    ).run {
                                        showIt.value = true
                                    }
                            },
                        tint = Color.Red,
                    )
                } else {
                    showIt.value = false
                }

                ShowToast(context = context, showIt)
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    if(showIt.value) {
        Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun ShowSettingsDropDown(
    showDialog: MutableState<Boolean>,
    navController: NavController
) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf(
        "About",
        "Favourites",
        "Settings"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 40.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(160.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (item) {
                            "About" -> Icons.Default.Info
                            "Favourites" -> Icons.Default.Favorite
                            else -> Icons.Default.Settings
                        },
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = item,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(
                                    when (item) {
                                        "About" -> WeatherScreens.AboutScreen.name
                                        "Favourites" -> WeatherScreens.FavouriteScreen.name
                                        else -> WeatherScreens.SettingsScreen.name
                                    }
                                )
                            },
                        fontWeight = FontWeight.W300
                    )
                }
            }
        }
    }
}
