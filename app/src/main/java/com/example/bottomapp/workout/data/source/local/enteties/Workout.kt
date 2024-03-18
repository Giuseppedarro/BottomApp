package com.example.bottomapp.workout.data.source.local.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("workout_id")
    val workoutId: Long = 0,
    val name: String,
    @ColumnInfo(name = "set_count")
    val setsCount: Int,
)
