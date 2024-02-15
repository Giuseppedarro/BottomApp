package com.example.bottomapp.model

data class WorkoutState(
    val workoutId: Int = 0,
    val name: String,
    val exercises: List<ExerciseState>,
    val numberOfExercises: Int = exercises.size,
    val setsCount: Int = exercises.map { it.numberOfSets }.sumOf { it }
)
