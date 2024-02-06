package com.example.bottomapp.data

import com.example.bottomapp.data.enteties.Exercise
import com.example.bottomapp.data.enteties.Workout
import com.example.bottomapp.data.enteties.WorkoutWithExercises
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    suspend fun insertWorkout(workout: Workout): Long

    suspend fun insertExercise(exercise: Exercise)

    suspend fun insertAllExercises(exercises: List<Exercise>)

    suspend fun insertWorkoutWithExercises()

    fun getAllWorkouts(): Flow<List<Workout>>

    fun getAllWorkoutsWithExercises(): Flow<List<WorkoutWithExercises>>

    suspend fun deleteWorkout(workout: Workout)

    suspend fun deleteExercise(exercise: Exercise)

    suspend fun deleteAllExercises(exercises: List<Exercise>)

}

class OfflineWorkoutRepository(private val dao: WorkoutDao) : WorkoutRepository {

    override suspend fun insertWorkout(workout: Workout) = dao.insertWorkout(workout)

    override suspend fun insertExercise(exercise: Exercise) = dao.insertExercise(exercise)

    override suspend fun insertAllExercises(exercises: List<Exercise>) = dao.insertAllExercises(exercises)

    override suspend fun insertWorkoutWithExercises() {
        val workoutId = insertWorkout(Workout(name = "WorkoutName", setsCount = (1..10).random()))
        insertAllExercises(listOf(Exercise(exerciseName = "panca ${workoutId}", workoutId = workoutId.toInt())))
    }

    override fun getAllWorkouts(): Flow<List<Workout>> = dao.getAllWorkouts()

    override fun getAllWorkoutsWithExercises(): Flow<List<WorkoutWithExercises>> = dao.getAllWorkoutsWithExercises()

    override suspend fun deleteWorkout(workout: Workout) = dao.deleteWorkout(workout)

    override suspend fun deleteExercise(exercise: Exercise) =dao.deleteExercise(exercise)

    override suspend fun deleteAllExercises(exercises: List<Exercise>) = dao.deleteAllExercises(exercises)

}