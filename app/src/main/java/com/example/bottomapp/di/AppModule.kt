package com.example.bottomapp.di

import com.example.bottomapp.data.DefaultWorkoutsRepository
import com.example.bottomapp.data.WorkoutsRepository
import com.example.bottomapp.data.source.local.WorkoutDatabase
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.ui.viewmodels.WorkoutViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

        single { WorkoutDatabase.getWorkoutDatabase(get()).getDao() }

        single<WorkoutsRepository> { DefaultWorkoutsRepository(get()) }

        viewModel { WorkoutViewModel(get()) }

        viewModel { CurrentSessionViewModel() }




}