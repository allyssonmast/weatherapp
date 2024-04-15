package com.example.weatherapp.util.format

import java.text.SimpleDateFormat
import java.util.Locale

fun formatarData(dataString: String): String? {
    val formatoAtual = "yyyy-MM-dd hh:mm"
    val novoFormato = "EEEE HH:mm"
    val formato = SimpleDateFormat(formatoAtual, Locale.getDefault())
    val data = formato.parse(dataString)
    val novoFormatoData = SimpleDateFormat(novoFormato, Locale.getDefault())
    return data?.let { novoFormatoData.format(it) }
}