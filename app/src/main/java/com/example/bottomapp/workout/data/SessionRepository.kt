package com.example.bottomapp.workout.data

import com.example.bottomapp.workout.data.source.local.AllExercises
import com.example.bottomapp.workout.domain.models.ExerciseState
import com.example.bottomapp.workout.domain.models.SetState
import com.example.bottomapp.workout.domain.models.WorkoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface SessionRepository {
    val workoutState: StateFlow<WorkoutState>
    fun addExerciseToWorkout(exercise: ExerciseState)
    fun updateWorkoutState(workoutState: WorkoutState)
    fun terminateWorkout()
    fun addSetToExercise(exerciseIndex: Int)
    fun setWeight(exerciseIndex: Int, setIndex: Int, weight: Int)
    fun setRepetitions(exerciseIndex: Int, setIndex: Int, repetitions: Int)
    fun completeSet(exerciseIndex: Int, setIndex: Int)
}

class CurrentSessionRepository : SessionRepository {


    private val _workoutState = MutableStateFlow<WorkoutState>(WorkoutState())
    override val workoutState = _workoutState.asStateFlow()

    val allExercises  = AllExercises.allExercises

    override fun addExerciseToWorkout(exercise: ExerciseState) {
        _workoutState.update { currentState ->
            currentState.copy(
                exercises = currentState.exercises + exercise,
                numberOfExercises = currentState.exercises.size + 1
            )
        }
    }

    override fun updateWorkoutState(workoutState: WorkoutState) {
        _workoutState.update { workoutState.copy(sessionState = true) }
    }

    override fun terminateWorkout() {
        _workoutState.update { currentState -> currentState.copy(sessionState = false) }
    }

    override fun addSetToExercise(
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

    override fun setWeight(
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

    override fun setRepetitions(
        exerciseIndex: Int,
        setIndex: Int,
        repetitions: Int
    ) {
        _workoutState.update { currentState  ->
            val newExercises = currentState.exercises.mapIndexed { exerciseMapIndex, exercise ->
                if (exerciseMapIndex == exerciseIndex) {
                    val newSets = exercise.sets.mapIndexed { setMapIndex, set ->
                        if (setMapIndex == setIndex) {
                            set.copy(repetitions = repetitions)
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

    override fun completeSet(
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