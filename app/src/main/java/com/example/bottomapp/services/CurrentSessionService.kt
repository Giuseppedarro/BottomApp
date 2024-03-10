package com.example.bottomapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CurrentSessionService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

}