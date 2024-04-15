package com.example.weatherapp.util.connetivity

import javax.inject.Inject

class GetConnectivity @Inject constructor(
    private var appStatus: AppStatus
) {
    fun isConnected(): Boolean{
        return appStatus.isNetworkAvailable()
    }
}