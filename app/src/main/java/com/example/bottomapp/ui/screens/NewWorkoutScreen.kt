package com.example.bottomapp.ui.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottomapp.R
import com.example.bottomapp.model.ExerciseState
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.ui.components.TableCell
import com.example.bottomapp.ui.navigation.WorkoutNavScreen

@Composable
fun NewWorkoutScreen(
    navigateToExercises: () -> Unit,
    paddingValues: PaddingValues,
    workoutState: WorkoutState,
    addSet: (exerciseIndex: Int,exercise: ExerciseState) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = """workout: ${workoutState.name}
            |number of exercises: ${workoutState.numberOfExercises}
            |number of sets: ${workoutState.numberOfExercises}
            |
        """.trimMargin(),
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            workoutState.exercises.forEachIndexed { exerciseIndex, it ->
                Text(
                    text = it.exerciseName,
                    fontSize = 20.sp
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TableCell(text = stringResource(id = R.string.set).uppercase())
                    TableCell(text = stringResource(id = R.string.kg).uppercase())
                    TableCell(text = stringResource(id = R.string.repetitions).uppercase())
                }
                it.sets.forEachIndexed { index, set ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TableCell(text = (index + 1).toString())
                        TableCell(text = set.weight.toString())
                        TableCell(text = set.repetitions.toString())
                    }
                }
                Button(onClick = { addSet(exerciseIndex,it) }){
                    Text(text = "add set")
                }
            }
        }
        Button(onClick = { navigateToExercises() }, modifier = Modifier.padding(20.dp)) {
            Text(text = "add new exercise")
        }
    }
}
