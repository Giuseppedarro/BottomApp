package com.example.bottomapp.workout.di

import com.example.bottomapp.workout.data.CurrentSessionRepository
import com.example.bottomapp.workout.data.DefaultWorkoutsRepository
import com.example.bottomapp.workout.data.SessionRepository
import com.example.bottomapp.workout.data.WorkoutsRepository
import com.example.bottomapp.workout.data.source.local.WorkoutDatabase
import com.example.bottomapp.workout.presentation.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.workout.presentation.viewmodels.WorkoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val workoutKoinModule = module {

        includes(workoutDatabaseKoinModule)
        single { get<WorkoutDatabase>().getDao() }
        single<WorkoutsRepository> { DefaultWorkoutsRepository(get()) }
        viewModel { WorkoutViewModel(get()) }
        single<SessionRepository> { CurrentSessionRepository() }
        viewModel { CurrentSessionViewModel(get()) }

}