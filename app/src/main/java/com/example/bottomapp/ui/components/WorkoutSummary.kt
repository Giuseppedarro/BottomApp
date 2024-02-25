package com.example.bottomapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.bottomapp.model.WorkoutState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WorkoutSummary(
    workout: WorkoutState,
    onDelete: suspend (workout: WorkoutState) -> Unit,
    onClick: () -> Unit,
    scope: CoroutineScope
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .clip(ShapeDefaults.Large)
            .shadow(20.dp)
            .clickable(onClick =  onClick ),
        elevation = CardDefaults.cardElevation(defaultElevation = 200.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = workout.name,
                fontWeight = FontWeight.Bold
            )
            workout.exercises.forEach { exercise ->
                Text(text = "${exercise.numberOfSets} x ${exercise.exerciseName}")
            }
        }
        IconButton(onClick = { scope.launch(Dispatchers.IO) { onDelete(workout) }
        }) {
            Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
        }
    }
}