package com.example.bottomapp.ui.screens

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomapp.CurrentSessionService
import com.example.bottomapp.ui.navigation.NavDestination
import com.example.bottomapp.ui.theme.BottomAppTheme
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.ui.viewmodels.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.core.Koin

@Composable
fun HomeScreen(paddingValues: PaddingValues) {


    
}

@Preview
@Composable
fun HomeScreenPreview() {
    BottomAppTheme {
        HomeScreen(paddingValues = PaddingValues())
    }
}