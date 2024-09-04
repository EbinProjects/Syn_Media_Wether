package com.example.synmediawether.repository

import android.content.Context
import android.content.SharedPreferences

interface DIInterface {
    val apiServices: ApiInterface
    val apiRepository: WetherRepository
    val context: Context
    val sharedPreferences: SharedPreferences
}