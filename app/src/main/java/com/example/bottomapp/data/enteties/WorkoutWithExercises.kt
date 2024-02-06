package com.example.bottomapp.data.enteties

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class WorkoutWithExercises(
    @Embedded
    val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val exercises: List<Exercise>
)
