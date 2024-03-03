package com.example.bottomapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottomapp.R
import com.example.bottomapp.ui.components.TableCell
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel

@Composable
fun NewWorkoutScreen(
    navigateToExercises: () -> Unit,
    paddingValues: PaddingValues,

    addSet: (exerciseIndex: Int) -> Unit,
    sessionViewModel: CurrentSessionViewModel
) {
    val workoutState by sessionViewModel.workoutState.collectAsState()

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
            |number of sets: ${workoutState.setsCount}
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
                    TableCell(content = stringResource(id = R.string.set).uppercase())
                    TableCell(content = stringResource(id = R.string.kg).uppercase())
                    TableCell(content = stringResource(id = R.string.repetitions).uppercase())
                    TableCell(content = Unit)
                }
                it.sets.forEachIndexed { index, set ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TableCell(content = (index + 1).toString())
                        TableCell(content = set.weight.toString())
                        TableCell(content = set.repetitions.toString())
                        Checkbox(
                            checked = set.isCompleted,
                            onCheckedChange = { sessionViewModel.completeSet(
                                exerciseIndex = exerciseIndex,
                                setIndex = index
                            )
                            },
                            modifier = Modifier.weight(1F)
                        )
                    }
                }
                Button(onClick = { addSet(exerciseIndex) }){
                    Text(text = "add set")
                }
            }
        }
        Button(onClick = { navigateToExercises() }, modifier = Modifier.padding(20.dp)) {
            Text(text = "add new exercise")
        }
    }
}
