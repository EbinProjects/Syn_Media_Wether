package com.example.synmediawether.Utilsss

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class Presentations {
    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        fun getDate(): String? {
            val currentDate = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy h:mm a", Locale.ENGLISH)
            return currentDate.format(formatter)
        }
    }
}