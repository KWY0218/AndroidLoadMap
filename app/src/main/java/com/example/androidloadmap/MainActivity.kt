package com.example.androidloadmap

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidloadmap.ui.theme.AndroidLoadMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        Log.d("MainActivityTAG", "Btn Click")
                    }
                ) {
                    Text(text = "BTN")
                }
            }
        }
        Log.d("MainActivityTAG", "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivityTAG", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivityTAG", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivityTAG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivityTAG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivityTAG", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivityTAG", "onDestroy")
    }
}