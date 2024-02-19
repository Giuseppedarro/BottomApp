package com.example.bottomapp.data.source.local.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "training_sets",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exercise_id"],
            childColumns = ["exercise_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TrainingSet (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("training_set_id")
    val trainingSetId: Long = 0,
    @ColumnInfo("exercise_id")
    val exerciseId: Long,
    val weight: Int,
    val repetitions: Int
)