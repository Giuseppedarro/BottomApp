package com.example.bottomapp

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.bottomapp.ui.BottomApp
import com.example.bottomapp.ui.theme.BottomAppTheme
import com.example.bottomapp.workout.presentation.viewmodels.CurrentSessionViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("create")
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
        installSplashScreen()
        setContent {
            BottomAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomApp()
                }
            }
        }

    }

    override fun onStop() {
        super.onStop()
        Intent(applicationContext, CurrentSessionService::class.java).also {
            it.action = CurrentSessionService.Action.START.toString()
            startService(it)
        }

    }


}


