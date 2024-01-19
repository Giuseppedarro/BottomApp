package com.example.bottomapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomapp.BottomApplication
import com.example.bottomapp.data.WorkoutRepository

class WorkoutViewModel(val workoutRepository: WorkoutRepository) : ViewModel() {

 companion object {
     val Factory: ViewModelProvider.Factory = viewModelFactory {
         initializer {
             val application = this[APPLICATION_KEY] as BottomApplication
             val workoutRepository: WorkoutRepository = application.container.workoutRepository
             WorkoutViewModel(workoutRepository = workoutRepository)
         }
     }
 }
}