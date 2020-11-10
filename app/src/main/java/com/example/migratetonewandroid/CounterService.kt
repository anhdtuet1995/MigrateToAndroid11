package com.example.migratetonewandroid

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class CounterService: Service() {

    private val TAG = "anh.dt2+"
    private var binder = LocalCounterService()

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG,"onBind")
        return this.binder;
    }

    override fun onCreate() {
        super.onCreate()
        Thread {
            for (i in 1..10000) {
                Log.i(TAG, "onCreate $i");
                Thread.sleep(1000)
            }
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG,"onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onRebind(intent: Intent?) {
        Log.i(TAG,"onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "onUnbind")
        return true;
    }

    inner class LocalCounterService: Binder() {
        fun getService(): CounterService {
            return this@CounterService
        }
    }

}