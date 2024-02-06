package com.example.bottomapp.data

import android.content.Context

interface AppContainer {
    val workoutRepository: WorkoutRepository
}

class DefaultAppContainer(context: Context) : AppContainer {

    override val workoutRepository: WorkoutRepository by lazy {
        OfflineWorkoutRepository(
            WorkoutDatabase
                .getWorkoutDatabase(context)
                .getDao()
        )
    }
}