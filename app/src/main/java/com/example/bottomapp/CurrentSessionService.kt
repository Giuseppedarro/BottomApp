package com.example.bottomapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.bottomapp.workout.data.SessionRepository
import org.koin.android.ext.android.inject

class CurrentSessionService() : Service() {

    private val sessionRepository: SessionRepository by inject()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            Action.START.toString() -> start()
            Action.STOP.toString() -> stopSelf()
        }



        return super.onStartCommand(intent, flags, startId)

    }

    private fun start() {


        val notification = NotificationCompat.Builder(this, "current_session_channel")
            .setSmallIcon(R.drawable.dumbbel_svgrepo_com)
            .setContentTitle(sessionRepository.workoutState.value.name)
            .setContentText(sessionRepository.workoutState.value.sessionState.toString())
            .build()
        startForeground(1, notification)
    }

    enum class Action {
        START,
        STOP
    }

}

