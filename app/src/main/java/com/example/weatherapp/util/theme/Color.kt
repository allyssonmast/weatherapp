package com.example.weatherapp.util.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val colorBg1 = Color(0xff08203e)
val colorBg2 = Color(0xff557c93)

val gradient = Brush.linearGradient(
    colors = listOf(colorBg1, colorBg2),
    start = Offset(1000f, -1000f),
    end = Offset(1000f, 1000f)
)