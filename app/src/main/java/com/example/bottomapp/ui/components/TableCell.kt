package com.example.bottomapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun <T>RowScope.TableCell(
    content: T,

    ) {

    when(content) {
        is String -> {
            Text(
                text = content,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }

        else -> Spacer(modifier = Modifier.weight(1F))
    }


}

@Preview
@Composable
fun TableCellPreview() {
    val provaRow = listOf("prova1","prova2","prova3")
    Row {
        provaRow.forEach { TableCell(content = it) }
    }
}