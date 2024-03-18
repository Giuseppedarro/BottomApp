package com.example.bottomapp.workout.domain.models

data class SetState(
    val weight: Int = 0,
    val repetitions: Int = 0,
    val isCompleted: Boolean = false
)
