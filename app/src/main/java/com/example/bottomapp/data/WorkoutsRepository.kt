package com.example.bottomapp.data

import com.example.bottomapp.data.source.local.AllExercises
import com.example.bottomapp.data.source.local.WorkoutDao
import com.example.bottomapp.model.ExerciseState
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.model.toWorkout
import com.example.bottomapp.model.toWorkoutState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface WorkoutsRepository {

    fun getWorkoutsFlow(): Flow<List<WorkoutState>>

    fun getExercisesList(): List<ExerciseState>

    suspend fun insertWorkoutWithExercisesAndSets(workoutState: WorkoutState)

    suspend fun deleteWorkout(workout: WorkoutState)
}


class DefaultWorkoutsRepository(private val dao: WorkoutDao) : WorkoutsRepository {

    override fun getWorkoutsFlow(): Flow<List<WorkoutState>> =
        dao.getWorkoutsWithExercisesAndSetsFlow()
            .map { it -> it.map { it.toWorkoutState() } }
            .flowOn(Dispatchers.IO)

    override fun getExercisesList(): List<ExerciseState> = AllExercises.allExercises

    override suspend fun insertWorkoutWithExercisesAndSets(workoutState: WorkoutState) =
        dao.insertWorkoutWithExercisesAndSets(workoutState)

    override suspend fun deleteWorkout(workout: WorkoutState) {
        dao.deleteWorkout(workout = workout.toWorkout())
    }

}