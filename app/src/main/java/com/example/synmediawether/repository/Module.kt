package com.example.synmediawether.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.synmediawether.Utilsss.Constants
import com.example.synmediawether.WeatherApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Module(private val appContext : WeatherApplication) : DIInterface{

    override val apiServices: ApiInterface by lazy {
        createApiServices(Constants.BASE_URL)
    }
    fun createApiServices(baseUrl:String):ApiInterface{
        val logger = HttpLoggingInterceptor { message ->
            Log.d("ApiServices" , "createAccessFlow: $message")
        }
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient =
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // Adjust timeout as needed
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS) .addInterceptor(logger).build()
        return   Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    override val apiRepository: WetherRepository by lazy {
        WetherRepository(apiServices)
    }


    override val context: Context by lazy {
        appContext
    }
    override val sharedPreferences: SharedPreferences by lazy {
        appContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

}