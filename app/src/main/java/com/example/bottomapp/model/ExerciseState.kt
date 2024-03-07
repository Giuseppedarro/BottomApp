package com.example.bottomapp.model

data class ExerciseState(
    val exerciseName: String,
    val sets: List<SetState> = emptyList(),
    val numberOfSets: Int = sets.size
)