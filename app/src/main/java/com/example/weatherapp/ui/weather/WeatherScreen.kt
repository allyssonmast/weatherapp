package com.example.weatherapp.ui.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.navigation.NavController
import com.example.weatherapp.util.theme.PurpleGrey80
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.weatherapp.ui.components.InternetConnectionDialog



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    navController: NavController,
    state: WeatherState,
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "fab")
                Text(text = "Search for city")
            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults
                    .mediumTopAppBarColors(
                        containerColor = PurpleGrey80
                    ),
                title = {
                    Text(text = "WeatherApp")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")

                    }
                }
            )
        }
    ) { paddingValues ->
        val openDialog = remember { mutableStateOf(!state.isConnected && !state.isLoading) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PurpleGrey80)
        ) {
            if (state.weather != null)
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Text(
                        text = "Fetch",
                        color = Color.Green,
                    )
                }
            state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (state.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            if (!state.isConnected && !state.isLoading)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Try again, no internet connection",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            if (openDialog.value)
                InternetConnectionDialog(
                    onDismissButton = { openDialog.value = false }
                )
        }
    }
}