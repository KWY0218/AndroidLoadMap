package com.example.androidloadmap.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class MainService : Service() {

    // client 한테 제공되는 바인더
    private val binder = LocalBinder()

    private val mGenerator = Random()

    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    // client 바인더에 사용되는 class
    inner class LocalBinder: Binder() {
        // Return this instance of LocalService so clients can call public methods
        // MainService 의 현재 인스턴스를 검색하기 위한 메서드
        fun getService(): MainService = this@MainService
    }

//    // 서비스가 생성되는 곳
//    override fun onCreate() {
//        super.onCreate()
//    }
//
//    // startService() 호출로 인해 서비스를 시작하는 곳
//   override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)
//    }

    // 클라이언트가 bindService() 를 사용해서 서비스에 바인딩 하는 곳
    override fun onBind(p0: Intent?): IBinder {
        Log.d("MainService","onBind")
        return binder
    }

//    // 클라이언트가 unbindService() 를 사용해서 바인딩을 해제하는 곳
//    override fun onUnbind(intent: Intent?): Boolean {
//        return super.onUnbind(intent)
//    }
//
//    // A client is binding to the service with bindService(),
//    // after onUnbind() has already been called
//    override fun onRebind(intent: Intent?) {
//        super.onRebind(intent)
//    }
//
//    // 서비스가 더이상 사용되지 않을 때 파괴되는 곳
//    override fun onDestroy() {
//        super.onDestroy()
//    }
}