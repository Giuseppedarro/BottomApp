package com.example.bottomapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomapp.BottomApplication
import com.example.bottomapp.data.Workout
import com.example.bottomapp.data.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class WorkoutViewModel(
    val workoutRepository: WorkoutRepository
) : ViewModel() {

    val workoutState: StateFlow<List<Workout>> = workoutRepository.getWorkout()
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    suspend fun insertWorkout(workout: Workout) = workoutRepository.insertWorkout(workout)

    suspend fun deleteWorkout(workout: Workout) = workoutRepository.deleteWorkout(workout)

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