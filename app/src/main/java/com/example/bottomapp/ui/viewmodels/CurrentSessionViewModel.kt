package com.example.bottomapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bottomapp.data.source.local.AllExercises
import com.example.bottomapp.model.ExerciseState
import com.example.bottomapp.model.SetState
import com.example.bottomapp.model.WorkoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CurrentSessionViewModel() : ViewModel() {

    private val _workoutState = MutableStateFlow<WorkoutState>(WorkoutState())
    val workoutState = _workoutState.asStateFlow()

    val allExercises  = AllExercises.allExercises

    fun addExerciseToWorkout(exercise: ExerciseState) {
        _workoutState.update { currentState ->
            currentState.copy(
                exercises = currentState.exercises + exercise,
                numberOfExercises = currentState.exercises.size + 1
            )
        }
    }

    fun updateWorkoutState(workoutState: WorkoutState) {
        _workoutState.update { workoutState.copy(sessionState = true) }
    }

    fun terminateWorkout() {
        _workoutState.update { currentState -> currentState.copy(sessionState = false) }
    }

    fun addSetToExercise(
        exerciseIndex: Int,
    ) {
        _workoutState.update { currentState ->

            val newExercises = currentState.exercises.mapIndexed { index, it ->
                if (index == exerciseIndex)
                    ExerciseState(
                        exerciseName = it.exerciseName,
                        sets = it.sets + (it.sets.lastOrNull()?.copy(isCompleted = false) ?: SetState()),
                    )
                else
                    it
            }
            currentState.copy(
                exercises = newExercises,
                numberOfExercises = newExercises.size,
                setsCount = newExercises.map { it.numberOfSets }.sumOf { it }
            )
        }
    }

    fun setWeight(
        exerciseIndex: Int,
        setIndex: Int,
        weight: Int
    ) {
        _workoutState.update { currentState  ->
            val newExercises = currentState.exercises.mapIndexed { exerciseMapIndex, exercise ->
                if (exerciseMapIndex == exerciseIndex) {
                    val newSets = exercise.sets.mapIndexed { setMapIndex, set ->
                        if (setMapIndex == setIndex) {
                            set.copy(weight = weight)
                        } else {
                            set
                        }
                    }
                    exercise.copy(sets = newSets)
                } else {
                    exercise
                }

            }


            currentState.copy(exercises = newExercises)
        }
    }

    fun completeSet(
        exerciseIndex: Int,
        setIndex: Int
    ) {
        _workoutState.update { currentState ->

            val newExercises = currentState.exercises.mapIndexed { exerciseMapIndex, exercise ->
                if (exerciseMapIndex == exerciseIndex) {
                    val newSets = exercise.sets.mapIndexed { setMapIndex, set ->
                        if (setMapIndex == setIndex) {
                            set.copy(isCompleted = !set.isCompleted)
                        } else {
                            set
                        }
                    }
                    exercise.copy(sets = newSets)
                } else {
                    exercise
                }
            }
            currentState.copy(exercises = newExercises)
        }
    }

}