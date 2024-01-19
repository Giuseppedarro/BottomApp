package com.example.bottomapp.data

import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    suspend fun insertWorkout(workout: Workout)

    fun getWorkout(): Flow<List<Workout>>

}

class OfflineWorkoutRepository(private val dao: WorkoutDao) : WorkoutRepository {

    override suspend fun insertWorkout(workout: Workout) = dao.insertWorkout(workout)

    override fun getWorkout(): Flow<List<Workout>> = dao.getAllWorkouts()

}