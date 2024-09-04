package com.example.synmediawether.Utilsss


sealed class Screens(val route:String){
    data object homeScreen : Screens("homeScreen")
    data object Splash : Screens("Splash")


}