package com.example.synmediawether.Utilsss

import android.content.Context
import android.widget.Toast

object ToastClas {
    fun showToast(context: Context,txt : String){
        Toast.makeText(context,txt,Toast.LENGTH_SHORT).show()
    }
}