package com.example.bottomapp.workout.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomapp.workout.data.WorkoutsRepository
import com.example.bottomapp.workout.domain.models.ExerciseState
import com.example.bottomapp.workout.domain.models.SetState
import com.example.bottomapp.workout.domain.models.WorkoutState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val workoutRepository: WorkoutsRepository
) : ViewModel() {

    private val _workoutsState: MutableStateFlow<List<WorkoutState>> = MutableStateFlow(emptyList())
    val workoutsState: StateFlow<List<WorkoutState>> = _workoutsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            insertRandomWorkout()
            workoutRepository.getWorkoutsFlow().collect { latestState ->
                _workoutsState.update { latestState }
            }

        }

    }

    private suspend fun insertRandomWorkout() {
        repeat(3) {
            val exercises = listOf(
                ExerciseState(exerciseName = "military", sets = listOf<SetState>(SetState(100,5, true))),
                ExerciseState(exerciseName = "panca", sets = listOf<SetState>(SetState(150,1, true))),
                ExerciseState(exerciseName = "squat", sets = listOf<SetState>(SetState(100,10, true)))
            )
            val workout = WorkoutState(
                name = "January ${(1..31).random()}",
                exercises = exercises
            )
            saveWorkout(workout)
        }
    }

    suspend fun saveWorkout(workout: WorkoutState) = workoutRepository.insertWorkoutWithExercisesAndSets(workout)
    suspend fun deleteWorkout(workout: WorkoutState) = workoutRepository.deleteWorkout(workout)


}