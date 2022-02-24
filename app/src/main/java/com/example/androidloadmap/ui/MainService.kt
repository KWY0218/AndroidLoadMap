package com.example.androidloadmap.ui

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast

// 메세지를 표시하도록 서비스에 명령한다.
const val MSG_SAY_HELLO = 1
class MainService : Service() {
    // 클라이언트가 IncomingHandler 에 메시지를 보내기 위한 대상
    private lateinit var mMessenger: Messenger

    // 클라이언트로부터 메시지를 받는 핸들러
    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                MSG_SAY_HELLO -> Toast.makeText(applicationContext, "hello",Toast.LENGTH_SHORT).show()
                else -> super.handleMessage(msg)
            }
        }
    }

    /*
        서비스가 바인딩될 때, 서비스에 메시지를 보내기 위해 매신저에 인터페이스를 반환한다
    */
    override fun onBind(p0: Intent?): IBinder? {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }

}