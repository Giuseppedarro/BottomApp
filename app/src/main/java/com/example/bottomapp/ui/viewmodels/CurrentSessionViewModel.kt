package com.example.bottomapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bottomapp.model.ExerciseState
import com.example.bottomapp.model.SetState
import com.example.bottomapp.model.WorkoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CurrentSessionViewModel : ViewModel() {

    private val _workoutState = MutableStateFlow<WorkoutState>(WorkoutState())
    val workoutState = _workoutState.asStateFlow()


    fun updateWorkoutState(workoutState: WorkoutState) {
        _workoutState.update { workoutState }
    }


    fun addSetToExercise(
        exerciseIndex: Int,
        exercise: ExerciseState
    ) {
        _workoutState.update { currentState ->

            currentState.copy(
                exercises = currentState.exercises.mapIndexed { index, it ->
                    if (index == exerciseIndex)
                        ExerciseState(
                            exerciseName = it.exerciseName,
                            sets = it.sets + SetState()
                        )
                    else
                    it

                }
            )
        }
    }

}