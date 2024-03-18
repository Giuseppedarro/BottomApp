package com.example.bottomapp.workout.data.source.local.enteties

import androidx.room.Embedded
import androidx.room.Relation


data class WorkoutWithExercisesAndSets(
    @Embedded
    val workout: Workout,
    @Relation(
        entity = Exercise::class,
        parentColumn = "workout_id",
        entityColumn = "workout_id"
    )
    val exercisesWithTrainingSets: List<ExerciseWithTrainingSets>
)