package com.example.weatherapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.weatherapp.util.constants.tags.Constants

@Composable
fun InternetConnectionDialog(
    onDismissButton: () -> Unit = {},
) {
    AlertDialog(
        modifier = Modifier.testTag(Constants.DIALOG),
            onDismissRequest={
               onDismissButton()
            },
            dismissButton = {
            },
            title = {
                Text("No Internet Connection")
            },
            text = {
                Text("Please check your internet connection and try again.")
            },
            confirmButton = {
                Button(onClick = {
                    onDismissButton()
                }) {
                    Text("OK")
                }
            }
        )
}
