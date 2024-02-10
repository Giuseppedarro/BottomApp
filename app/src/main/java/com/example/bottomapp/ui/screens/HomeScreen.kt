package com.example.bottomapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bottomapp.ui.navigation.NavDestination

@Composable
fun HomeScreen(paddingValues: PaddingValues) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = NavDestination.HomeDestination.title)
            )
        }
    }



}