package com.example.bottomapp.data.source.local.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("exercise_id")
    val exerciseId: Long = 0,
    @ColumnInfo("workout_id")
    val workoutId: Long,
    @ColumnInfo("exercise_name")
    val exerciseName: String

)
