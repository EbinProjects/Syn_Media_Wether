package com.example.synmediawether.viewmodelandfactory

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synmediawether.Utilsss.ApiResponse
import com.example.synmediawether.Utilsss.Constants
import com.example.synmediawether.Utilsss.Presentations
import com.example.synmediawether.pojo.WetherResponse
import com.example.synmediawether.repository.WetherRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WetherViewmodel(
    private val apiRepository: WetherRepository,
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
   private var job : Job? = null
    var lattitude : Double? = null
    var logittude : Double? = null
    var progress  = MutableLiveData(true)
    private val _weatherResponses = MutableStateFlow(WetherResponse())
    val weatherResponses: StateFlow<WetherResponse> get() = _weatherResponses

     @RequiresApi(Build.VERSION_CODES.O)
     fun fetchWeather(logittude : Double, lattitude :Double) {
         job?.cancel()
         job = viewModelScope.launch{
            apiRepository.getWeather(longitude = logittude, latitude = lattitude, key = Constants.API_KEY).collect { response ->
               when(response){
                  is ApiResponse.Loading ->{
                      progress.value = true
                  }
                   is ApiResponse.Success ->{
                       progress.value = false
                       _weatherResponses.value = response.data.apply { time = Presentations.getDate()  }
                       storeLocally(response.data.apply { time = Presentations.getDate()  })
                   }
                   is ApiResponse.Error ->{
                       progress.value = false
                       val data = sharedPreferences.getString("Local","")
                           val gson = Gson()
                     val localData = if (data?.isNotEmpty() == true) gson.fromJson(data, WetherResponse::class.java) else WetherResponse()
                       _weatherResponses.value = localData
                   }

               }
            }
        }
    }
fun storeLocally(response : WetherResponse?){
    val gson = Gson()
    val data = gson.toJson(response).toString()
    sharedPreferences.edit().apply {
        putString("Local",data)
    }.apply()
}

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}