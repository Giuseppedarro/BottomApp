package com.example.bottomapp.data.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("workout_id")
    val workoutId: Int,
    @ColumnInfo("exercise_name")
    val exerciseName: String

)
