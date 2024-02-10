package com.example.bottomapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.bottomapp.data.source.local.enteties.Exercise
import com.example.bottomapp.data.source.local.enteties.Workout
import com.example.bottomapp.data.source.local.enteties.WorkoutWithExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert
    suspend fun insertWorkout(workout: Workout): Long

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Insert
    suspend fun insertAllExercises(exercises: List<Exercise>)

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): Flow<List<Workout>>

    @Transaction
    @Query("SELECT * FROM workouts")
    fun getAllWorkoutsWithExercises(): Flow<List<WorkoutWithExercises>>

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Delete
    suspend fun deleteAllExercises(exercises: List<Exercise>)

}