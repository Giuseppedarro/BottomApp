package com.example.bottomapp.workout.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bottomapp.workout.data.SessionRepository
import com.example.bottomapp.workout.data.source.local.AllExercises
import com.example.bottomapp.workout.domain.models.ExerciseState
import com.example.bottomapp.workout.domain.models.WorkoutState

class CurrentSessionViewModel(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    val workoutState = sessionRepository.workoutState

    val allExercises  = AllExercises.allExercises

    fun addExerciseToWorkout(exercise: ExerciseState) =
        sessionRepository.addExerciseToWorkout(exercise)

    fun updateWorkoutState(workoutState: WorkoutState) =
        sessionRepository.updateWorkoutState(workoutState)

    fun terminateWorkout() = sessionRepository.terminateWorkout()

    fun addSetToExercise(exerciseIndex: Int, ) = sessionRepository.addSetToExercise(exerciseIndex)

    fun setWeight(
        exerciseIndex: Int,
        setIndex: Int,
        weight: Int
    ) = sessionRepository.setWeight(exerciseIndex, setIndex, weight)

    fun setRepetitions(
        exerciseIndex: Int,
        setIndex: Int,
        repetitions: Int
    ) = sessionRepository.setRepetitions(exerciseIndex, setIndex, repetitions)

    fun completeSet(
        exerciseIndex: Int,
        setIndex: Int
    ) = sessionRepository.completeSet(exerciseIndex, setIndex)

}