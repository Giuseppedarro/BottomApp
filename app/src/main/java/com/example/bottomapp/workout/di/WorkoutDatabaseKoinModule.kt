package com.example.bottomapp.workout.di

import com.example.bottomapp.workout.data.source.local.WorkoutDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val workoutDatabaseKoinModule = module {
    single { WorkoutDatabase.getWorkoutDatabase(androidContext()) }
}