package com.example.bottomapp.home.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.bottomapp.ui.theme.BottomAppTheme

@Composable
fun HomeScreen(paddingValues: PaddingValues) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = { 0.5f })
        Text(text = "testo")
        val loremIpsum = LoremIpsum(50).values.first()
        Text(text = loremIpsum)
    }

    
}

@Preview
@Composable
fun HomeScreenPreview() {
    BottomAppTheme {
        HomeScreen(paddingValues = PaddingValues())
    }
}