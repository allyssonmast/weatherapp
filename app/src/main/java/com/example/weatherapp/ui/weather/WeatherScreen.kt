package com.example.weatherapp.ui.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.components.InternetConnectionDialog
import com.example.weatherapp.ui.components.WeatherHomeScreen
import com.example.weatherapp.util.theme.gradient

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    state: WeatherState, onSearch: (text: String) -> Unit
) {
    val text = remember { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }
    val items = remember {
        mutableStateListOf("Natal")
    }
    val openDialog = remember { mutableStateOf(!state.isConnected && !state.isLoading) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            SearchBar(modifier = Modifier.padding(horizontal = 20.dp),
                query = text.value,
                onQueryChange = {
                    text.value = it
                },
                onSearch = {
                    items.add(text.value)
                    active.value = false
                    onSearch(text.value)
                },
                active = active.value,
                onActiveChange = {
                    active.value = it
                },
                trailingIcon = {
                    if (active.value) {
                        Icon(
                            modifier = Modifier.clickable {
                                text.value = ""
                            }, imageVector = Icons.Default.Close, contentDescription = "close"
                        )

                    }
                },
                placeholder = {
                    Text(text = "Search a city")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                }
            ) {
                items.forEach {
                    Row(modifier = Modifier.padding(all = 14.dp)) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            imageVector = Icons.Filled.History,
                            contentDescription = "History Icon"
                        )
                        Text(text = it)

                    }
                }
            }

            if (state.weather != null || state.isLoading) state.weather?.let { it1 ->
                WeatherHomeScreen(
                    weather = it1,
                    update = {state.city?.let { onSearch(it) }}
                )
            }
            state.error?.let { error ->
                Text(
                    text = error, color = Color.Red, modifier = Modifier.align(Alignment.Center)
                )
            }

            if (state.isLoading) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            if (!state.isConnected && !state.isLoading) Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Try again, no internet connection",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (openDialog.value) InternetConnectionDialog(onDismissButton = {
                openDialog.value = false
            })

        }
}