package com.example.bottomapp.workout.data.source.local.enteties

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseWithTrainingSets(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "exercise_id",
        entityColumn = "exercise_id"
    )
    val trainingSets: List<TrainingSet>
)

