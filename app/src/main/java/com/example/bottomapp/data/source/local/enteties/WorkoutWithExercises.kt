package com.example.bottomapp.data.source.local.enteties

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class WorkoutWithExercises(
    @Embedded
    val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "workout_id"
    )
    val exercises: List<Exercise>
)