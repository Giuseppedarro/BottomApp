package com.example.bottomapp.data

import android.content.Context
import com.example.bottomapp.data.source.local.WorkoutDatabase

interface AppContainer {
    val workoutRepository: WorkoutRepository
}

class DefaultAppContainer(context: Context) : AppContainer {

    override val workoutRepository: WorkoutRepository by lazy {
        DefaultWorkoutRepository(
            WorkoutDatabase
                .getWorkoutDatabase(context)
                .getDao()
        )
    }
}