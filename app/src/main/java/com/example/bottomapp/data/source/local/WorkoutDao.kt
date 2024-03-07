package com.example.bottomapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.bottomapp.data.source.local.enteties.Exercise
import com.example.bottomapp.data.source.local.enteties.TrainingSet
import com.example.bottomapp.data.source.local.enteties.Workout
import com.example.bottomapp.data.source.local.enteties.WorkoutWithExercises
import com.example.bottomapp.data.source.local.enteties.WorkoutWithExercisesAndSets
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.model.toExercise
import com.example.bottomapp.model.toTrainingSet
import com.example.bottomapp.model.toWorkout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {


    @Transaction
    @Query("SELECT * FROM workouts")
    fun getWorkoutsWithExercisesAndSetsFlow(): Flow<List<WorkoutWithExercisesAndSets>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrainingSets(sets: List<TrainingSet>)

    @Transaction
    suspend fun insertWorkoutWithExercisesAndSets(workoutState: WorkoutState) {
        val workoutId = insertWorkout(workoutState.toWorkout())
        workoutState.exercises.forEach {
            if (it.numberOfSets >= 1)  {
                val exercise = it.toExercise(workoutId)
                val exerciseId = insertExercise(exercise)
                val trainingSets = it.sets.filter {  it.isCompleted}.map { it.toTrainingSet(exerciseId) }
                insertTrainingSets(trainingSets)
            }

        }
    }

    @Transaction
    @Delete
    suspend fun deleteWorkout(workout: Workout)

}