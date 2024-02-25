package com.example.bottomapp.model

import com.example.bottomapp.data.source.local.enteties.Exercise
import com.example.bottomapp.data.source.local.enteties.ExerciseWithTrainingSets
import com.example.bottomapp.data.source.local.enteties.TrainingSet
import com.example.bottomapp.data.source.local.enteties.Workout
import com.example.bottomapp.data.source.local.enteties.WorkoutWithExercisesAndSets

/*extension functions to convert workout to workout-state and vice versa*/
fun TrainingSet.toSetState() = SetState(
    weight = weight,
    repetitions= repetitions
)

fun ExerciseWithTrainingSets.toExerciseState() = ExerciseState(
    exerciseName = exercise.exerciseName,
    sets = trainingSets.map { it.toSetState() }
)

fun WorkoutWithExercisesAndSets.toWorkoutState() = WorkoutState(
    name = workout.name,
    workoutId = workout.workoutId,
    exercises = exercisesWithTrainingSets.map { it.toExerciseState() },

)

fun WorkoutState.toWorkout() = Workout(
    name = name,
    setsCount = setsCount,
    workoutId = workoutId
)

fun ExerciseState.toExercise(workoutId: Long) = Exercise(
    workoutId = workoutId,
    exerciseName = exerciseName
)

fun SetState.toTrainingSet(exerciseId: Long) = TrainingSet(
    exerciseId = exerciseId,
    weight = weight,
    repetitions = repetitions
)