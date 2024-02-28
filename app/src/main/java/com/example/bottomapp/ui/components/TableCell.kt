package com.example.bottomapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RowScope.TableCell(
    text: String,

) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
    )
}

@Preview
@Composable
fun TableCellPreview() {
    val provaRow = listOf("prova1","prova2","prova3")
    Row {
        provaRow.forEach { TableCell(text = it) }
    }
}