package com.example.migratetonewandroid

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView

class ExActivity : AppCompatActivity() {
    private val TAG = "anh.dt2";
    private var binded = false
    private var counterService: CounterService? = null

    private var serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "ExActivity onServiceDisconnected")
            binded = false
            val intent = Intent(this@ExActivity, CounterService::class.java)
            stopService(intent)
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(TAG, "ExActivity onServiceConnected")
            val binder = service as CounterService.LocalCounterService
            counterService = binder.getService()
            binded = true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex)

        val intent = Intent(this, CounterService::class.java)
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        findViewById<TextView>(R.id.abc).setOnClickListener {
            Log.i("anh.dt2", "stop service");
            unbindService(serviceConnection)
        }
    }

    override fun onStop() {
        super.onStop()
    }
}