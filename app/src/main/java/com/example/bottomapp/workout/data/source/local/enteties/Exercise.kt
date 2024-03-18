package com.example.bottomapp.workout.data.source.local.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["workout_id"],
            childColumns = ["workout_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("exercise_id")
    val exerciseId: Long = 0,
    @ColumnInfo("workout_id")
    val workoutId: Long,
    @ColumnInfo("exercise_name")
    val exerciseName: String

)
