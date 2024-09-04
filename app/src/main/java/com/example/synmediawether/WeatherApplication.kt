package com.example.synmediawether

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.example.synmediawether.repository.Module
import com.example.synmediawether.viewmodelandfactory.WetherViewmodeFactory
import com.example.synmediawether.viewmodelandfactory.WetherViewmodel

class WeatherApplication :Application(), ViewModelStoreOwner {
    override val viewModelStore by lazy { ViewModelStore() }
    lateinit var weatherViewmodel: WetherViewmodel

    companion object{
        lateinit var dependencyInjectModule: Module
        lateinit var appContext : Context
    }

    override fun onCreate() {
        super.onCreate()
        dependencyInjectModule = Module(this)
        appContext=this
        initViewModel()
    }
    fun initViewModel() {
        val factory = WetherViewmodeFactory(
            dependencyInjectModule.apiRepository,
            dependencyInjectModule.context,
            dependencyInjectModule.sharedPreferences,
        )
        weatherViewmodel = ViewModelProvider(this, factory)[WetherViewmodel::class.java]
    }
}