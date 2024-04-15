package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.util.format.formatarData

@Composable
fun WeatherHomeScreen(
    weather: Weather,
    update: () -> Unit
) {
    Box(
        modifier = Modifier.padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    formatarData(weather.localtime)!!,
                    style = TextStyle(
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.End,
                        fontSize = 20.sp
                    )
                )
            }
            ImageWeather(icon = weather.iconUrl)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "${weather.temperature} °C",
                    style = TextStyle(fontWeight = FontWeight.W700, fontSize = 30.sp)
                )
                Text(
                    weather.name,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(weather.country)
                Text(weather.weatherDescription)
            }
            CustomerDividerHor()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                WeatherItem(
                    icon = Icons.Filled.DeviceThermostat,
                    title = "Feels Like",
                    temp = "${weather.feelsLike} °C"
                )
                CustomerDivider()
                WeatherItem(
                    icon = Icons.Filled.WaterDrop,
                    title = "Humidity",
                    temp = "${weather.humidity} %"
                )
                CustomerDivider()
                WeatherItem(
                    icon = Icons.Filled.Air,
                    title = "Wind",
                    temp = "${weather.windSpeed} m/s"
                )
            }
            ElevatedButton(
                onClick = { update() }
            ) { Text("Update") }
        }
    }

}

@Composable
fun ImageWeather(icon: String) {
    Card() {
        AsyncImage(
            model = icon,
            contentDescription = icon,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp),
            contentScale = ContentScale.FillBounds
        )
    }

}

@Composable
fun CustomerDivider() {
    VerticalDivider(
        thickness = 1.dp,
        color = Color.White,
        modifier = Modifier
            .width(0.5.dp)
            .height(40.dp)
    )
}

@Composable
fun CustomerDividerHor() {
    HorizontalDivider(
        thickness = 1.dp,
        color = Color.White,
        modifier = Modifier
            .width(40.dp)
            .height(0.5.dp)
    )
}

@Composable
fun WeatherItem(icon: ImageVector, title: String, temp: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            icon, contentDescription = null, modifier = Modifier
                .width(50.dp)
                .height(50.dp)
        )
        Text(title, fontSize = 20.sp)
        Text(temp, fontSize = 25.sp)
    }
}