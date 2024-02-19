package com.example.bottomapp.data

import com.example.bottomapp.data.source.local.enteties.Exercise
import com.example.bottomapp.data.source.local.enteties.Workout
import com.example.bottomapp.data.source.local.enteties.WorkoutWithExercises
import com.example.bottomapp.data.source.local.WorkoutDao
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.model.toWorkout
import com.example.bottomapp.model.toWorkoutState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface WorkoutRepository {

    fun getWorkoutsFlow(): Flow<List<WorkoutState>>

    suspend fun insertWorkoutWithExercisesAndSets(workoutState: WorkoutState)

    suspend fun deleteWorkout(workout: WorkoutState)
}


class DefaultWorkoutRepository(private val dao: WorkoutDao) : WorkoutRepository {

    override fun getWorkoutsFlow(): Flow<List<WorkoutState>> =
        dao.getWorkoutsWithExercisesAndSetsFlow()
            .map { it -> it.map { it.toWorkoutState() } }
            .flowOn(Dispatchers.IO)

    override suspend fun insertWorkoutWithExercisesAndSets(workoutState: WorkoutState) =
        dao.insertWorkoutWithExercisesAndSets(workoutState)

    override suspend fun deleteWorkout(workout: WorkoutState) {
        dao.deleteWorkout(workout = workout.toWorkout())
    }

}