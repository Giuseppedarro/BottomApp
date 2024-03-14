package com.example.bottomapp

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.ui.BottomApp
import com.example.bottomapp.ui.theme.BottomAppTheme
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.ui.viewmodels.WorkoutViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {


    private lateinit var sessionViewModel: CurrentSessionViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("create")
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
        installSplashScreen()
        setContent {
            sessionViewModel = getViewModel()
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

    override fun onPause() {
        super.onPause()
        println("pause")
        val workoutState = sessionViewModel.workoutState.value
        if (workoutState.sessionState) {
            Intent(applicationContext, CurrentSessionService::class.java).also {
                it.action = CurrentSessionService.Action.START.toString()
                startService(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        println("resume")
    }

    override fun onStop() {
        super.onStop()
        println("stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("destroy")
    }

}


