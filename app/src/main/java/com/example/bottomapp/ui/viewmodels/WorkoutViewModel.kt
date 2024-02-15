package com.example.bottomapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomapp.BottomApplication
import com.example.bottomapp.data.WorkoutRepository
import com.example.bottomapp.data.source.local.enteties.Exercise
import com.example.bottomapp.model.ExerciseState
import com.example.bottomapp.model.SetState
import com.example.bottomapp.model.WorkoutState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkoutViewModel(
    val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _workoutState: MutableStateFlow<List<WorkoutState>> = MutableStateFlow(emptyList())
    val workoutState: StateFlow<List<WorkoutState>> = _workoutState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.getWorkoutsFlow().collect { latestState ->
                _workoutState.update { latestState }
            }
        }

    }

    suspend fun insertRandomWorkout() {
        val exercises = listOf<ExerciseState>(
            ExerciseState(exerciseName = "military", sets = listOf<SetState>(SetState(100,5))),
            ExerciseState(exerciseName = "panca", sets = listOf<SetState>(SetState(150,1))),
            ExerciseState(exerciseName = "squat", sets = listOf<SetState>(SetState(100,10)))
        )
        val workout = WorkoutState(
            name = "January ${(1..31).random()}",
            exercises = exercises

        )
        workoutRepository.insertWorkoutWithExercisesAndSets(workout)
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