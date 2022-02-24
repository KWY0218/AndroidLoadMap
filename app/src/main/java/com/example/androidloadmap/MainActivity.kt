package com.example.androidloadmap

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidloadmap.service.MainService
import com.example.androidloadmap.ui.screen.MainScreen

class MainActivity : ComponentActivity() {
    private lateinit var mService: MainService
    private var mBound: Boolean = false

    // 바인드 서비스에 전달된, 서비스 바인딩에 대한 콜백을 정의한다
    private val connection = object : ServiceConnection {
        // MainService 에 바인딩하고 IBinder 를 케스팅하고 MainService 인스턴스를 가져온다
        override fun onServiceConnected(p0: ComponentName, p1: IBinder) {
            val binder = p1 as MainService.LocalBinder
            mService = binder.getService()
            mBound = true
            Log.d("MainService", "onServiceConnected?, bound: $mBound")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
            Log.d("MainService", "onServiceDisconnected?, bound: $mBound")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen { onButtonClick() }
        }
    }

    override fun onStart() {
        super.onStart()
        // MainService 를 바인딩 한다.
        Intent(this, MainService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
            Log.d("MainService", "Bind?")
        }
        Log.d("MainService", "onStart")
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
        Log.d("MainService", "onStop")
    }

    fun onButtonClick() {
        if (mBound) {
            /*
                MainService 로 부터 메서드를 호출한다.
                만약 이 호출이 끊긴다면, 이 요청은 액티비티 수행에 속도저하를 피하기 위해 다른 스레드에서 일어나야 한다
            */
            val num: Int = mService.randomNumber
            Log.d("MainService", "onButtonClick, number: $num")
            Toast.makeText(this, "number: $num", Toast.LENGTH_SHORT).show()
        }
    }
}