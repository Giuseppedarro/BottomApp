package com.example.bottomapp.data

import android.content.Context
import com.example.bottomapp.data.source.local.WorkoutDatabase

interface AppContainer {
    val workoutRepository: WorkoutsRepository
}

class DefaultAppContainer(context: Context) : AppContainer {

    override val workoutRepository: WorkoutsRepository by lazy {
        DefaultWorkoutsRepository(
            WorkoutDatabase
                .getWorkoutDatabase(context)
                .getDao()
        )
    }
}