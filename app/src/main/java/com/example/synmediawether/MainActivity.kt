package com.example.synmediawether

import HomeScreenUI
import SplashUI
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.synmediawether.Utilsss.Constants.Companion.PERMISSION_ID
import com.example.synmediawether.Utilsss.Screens
import com.example.synmediawether.Utilsss.ToastClas
import com.example.synmediawether.ui.theme.SynmediaWetherTheme
import com.example.synmediawether.viewmodelandfactory.WetherViewmodel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class MainActivity : ComponentActivity() {
    private lateinit var yourViewModel: WetherViewmodel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myApplication = application as WeatherApplication
        yourViewModel = myApplication.weatherViewmodel
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (isLocationPermissionGranted()) {
            accessUserLocation()
        } else {
            requestLocationPermission()
        }
        setContent {
            SynmediaWetherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MyAppNavHost(navController = navController, viewModel = yourViewModel)
                }
            }
        }
    }
    private val requestLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
                accessUserLocation()
            }
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                accessUserLocation()
            }
            else -> {
                ToastClas.showToast(this@MainActivity,"Please turn on your location...")
            }
        }
    }
    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestLocationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    @SuppressLint("MissingPermission")
    private fun accessUserLocation() {
        if (isLocationEnabled()) {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000) // 5 seconds
                .setMinUpdateIntervalMillis(500) // 2 seconds
                .build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult ?: return
                    for (location in locationResult.locations) {
                        if ( yourViewModel.lattitude==null||yourViewModel.logittude==null) {
                            yourViewModel.lattitude = location.latitude
                            yourViewModel.logittude = location.longitude
                            yourViewModel.fetchWeather(
                                logittude = yourViewModel.logittude!!,
                                lattitude = yourViewModel.lattitude!!
                            )
                        }
                    }
                }
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
           return
        }
        ToastClas.showToast(this@MainActivity, "Please turn on your location and reopen...")
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
        finishAffinity()
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    override fun onStart() {
        super.onStart()

    }

}

@Composable
private fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: WetherViewmodel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashUI (navController =navController)
        }
        composable(route = Screens.homeScreen.route) {
            HomeScreenUI (navController = navController, viewmodel = viewModel)
        }

    }
}
