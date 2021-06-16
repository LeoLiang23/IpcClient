package com.example.ipcclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ipcservice.IMyAidlInterface

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LogClient"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.tvStartService).setOnClickListener {
            Log.d(TAG, "StartService click")
            startMyServer()
        }
        findViewById<View>(R.id.tvSayHi).setOnClickListener {
            Log.d(TAG, "SayHi click")
            mIMyAidlInterface?.sayHi("Hello my name is Pitt")
        }
        findViewById<View>(R.id.tvGetName).setOnClickListener {
            Log.d(TAG, "GetName click")
            Log.d(TAG, "GetName ${mIMyAidlInterface?.name ?: ""}")

        }
        findViewById<View>(R.id.tvGetAge).setOnClickListener {
            Log.d(TAG, "GetAge click")
            Log.d(TAG, "GetName ${mIMyAidlInterface?.age ?: ""}")
        }
    }

    private var mIMyAidlInterface: IMyAidlInterface? = null

    // 继承 ServiceConnection
    private val serviceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {// 服务断开
            Log.d(TAG, "onServiceDisconnected")
            mIMyAidlInterface = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected")
            // 服务连接成功 通过 Stub.asInterface 获取服务
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
        }
    }

    // 启动绑定服务
    private fun startMyServer() {
        val intent = Intent()
        // 包名 必须是我们新建aidl 的包名，因为这个包名也是服务对应的包名
        intent.setPackage("com.example.ipcservice")
        // 对应服务
        intent.action = "com.example.ipcservice.MyService"
        // 绑定服务
        applicationContext.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }
}