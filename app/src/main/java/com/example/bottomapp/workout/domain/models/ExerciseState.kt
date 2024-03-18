package com.example.bottomapp.workout.domain.models

import com.example.bottomapp.workout.domain.models.SetState

data class ExerciseState(
    val exerciseName: String,
    val sets: List<SetState> = emptyList(),
    val numberOfSets: Int = sets.size
)