package com.example.androidloadmap

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidloadmap.ui.MSG_SAY_HELLO
import com.example.androidloadmap.ui.MainService
import com.example.androidloadmap.ui.screen.MainScreen

class MainActivity : ComponentActivity() {
    // 서비스와 통신하기 위한 메신저
    private var mService: Messenger? = null
    // 서비스를 호출해야되는지 표시하는 플래그
    private var bound: Boolean = false
    // 서비스의 주 인터페이스와 상호작용하기 위한 클래스
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mService = Messenger(p1)
            bound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mService = null
            bound = false
        }

    }
    fun sayHello(){
        if (!bound) return
        val msg: Message = Message.obtain(null,MSG_SAY_HELLO,0,0)
        try {
            mService?.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen {
                sayHello()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MainService::class.java).also { intent ->
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if(bound) {
            unbindService(mConnection)
            bound = false
        }
    }
}