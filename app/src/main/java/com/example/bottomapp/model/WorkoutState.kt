package com.example.bottomapp.model

import java.time.LocalDate

data class WorkoutState(
    val workoutId: Long = 0,
    val name: String = "",
    val exercises: List<ExerciseState> = emptyList(),
    val numberOfExercises: Int = exercises.size,
    val setsCount: Int = exercises.map { it.numberOfSets }.sumOf { it },
    val sessionState: Boolean =  false,
)

