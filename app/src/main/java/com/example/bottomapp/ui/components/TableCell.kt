package com.example.bottomapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>RowScope.TableCell(
    content: T,
    onValueChange: ((Int) -> Unit)? = null

    ) {

    when(content) {
        is String -> {
            Text(
                text = content,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )
        }
        is Int -> {
            TextField(
                value = content.toString(),
                onValueChange = { newValue ->
                    if (newValue.length <= 3 && newValue.isNotBlank())
                        onValueChange?.invoke(newValue.toInt())
                    else if (newValue.isBlank()) onValueChange?.invoke(0)
                                },
                singleLine = true,
                shape = MaterialTheme.shapes.extraLarge,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1F)
                    .padding(12.dp)

        )


        }
        else -> Spacer(modifier = Modifier.weight(1F))
    }


}

@Preview
@Composable
fun TableCellPreview() {
    val provaRow = listOf("prova1","prova2","prova3","prova4")
    val provaRow2 = listOf("1", 2, 3)
    Column {
        Row {
            provaRow.forEach { TableCell(content = it) }
        }
        Row {
            provaRow2.forEach { TableCell(content = it) }
            Checkbox(checked = true, onCheckedChange = {}, modifier = Modifier
                .weight(1F)
                .align(alignment = Alignment.CenterVertically))
        }
    }

}