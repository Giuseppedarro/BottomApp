package com.example.bottomapp.di

import com.example.bottomapp.data.AppContainer
import com.example.bottomapp.data.DefaultAppContainer
import com.example.bottomapp.data.DefaultWorkoutRepository
import com.example.bottomapp.data.WorkoutRepository
import com.example.bottomapp.data.source.local.WorkoutDatabase
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.ui.viewmodels.WorkoutViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

        single { WorkoutDatabase.getWorkoutDatabase(androidContext()).getDao() }

        single<WorkoutRepository> { DefaultWorkoutRepository(get()) }

        viewModel { WorkoutViewModel(get()) }

}