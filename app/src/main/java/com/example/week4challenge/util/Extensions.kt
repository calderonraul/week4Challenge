package com.example.week4challenge.util

import android.content.Context
import com.example.week4challenge.R

fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception(this.resources.getString(R.string.no_network_connectivity)))
}