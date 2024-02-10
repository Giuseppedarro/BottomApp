package com.example.bottomapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomapp.BottomApplication
import com.example.bottomapp.data.source.local.enteties.Workout
import com.example.bottomapp.data.WorkoutRepository
import com.example.bottomapp.data.source.local.enteties.Exercise
import com.example.bottomapp.data.source.local.enteties.WorkoutWithExercises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkoutViewModel(
    val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _workoutState: MutableStateFlow<List<WorkoutWithExercises>> = MutableStateFlow(emptyList())
    val workoutState: StateFlow<List<WorkoutWithExercises>> = _workoutState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllWorkoutsWithExercises().collect { latestState ->
                _workoutState.update { latestState }
            }
        }

    }

    private fun getAllWorkouts() = workoutRepository.getAllWorkouts()

    private fun getAllWorkoutsWithExercises() = workoutRepository.getAllWorkoutsWithExercises()

    suspend fun insertWorkout(workout: Workout) = workoutRepository.insertWorkout(workout)

    suspend fun insertExercise(exercise: Exercise) = workoutRepository.insertExercise(exercise)

    suspend fun insertAllExercises(exercises: List<Exercise>) = workoutRepository.insertAllExercises(exercises)

    suspend fun insertWorkoutWithExercises() =  workoutRepository.insertWorkoutWithExercises()

    suspend fun deleteWorkout(workout: Workout) = workoutRepository.deleteWorkout(workout)

    suspend fun deleteAllExercises(exercises: List<Exercise>) = workoutRepository.deleteAllExercises(exercises)

    suspend fun deleteWorkoutWithExercises(workoutWithExercises: WorkoutWithExercises){
        deleteAllExercises(workoutWithExercises.exercises)
        deleteWorkout(workoutWithExercises.workout)
    }

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