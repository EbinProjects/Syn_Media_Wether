package com.example.synmediawether.viewmodelandfactory

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.synmediawether.repository.WetherRepository


class WetherViewmodeFactory(
    private val apiRepository: WetherRepository,
    private val context: Context,
    private val sharedPreferences: SharedPreferences,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(WetherViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WetherViewmodel(
                apiRepository,
                context,
                sharedPreferences,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}