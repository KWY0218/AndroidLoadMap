package com.example.androidloadmap

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.androidloadmap.ui.MainService
import com.example.androidloadmap.ui.screen.MainScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                {
                    Intent(this, MainService::class.java).also { intent ->
                        startForegroundService(intent)
                    }
                }
            ) {
                Intent(this, MainService::class.java).also { intent ->
                    stopService(intent)
                }
            }
        }
    }
}