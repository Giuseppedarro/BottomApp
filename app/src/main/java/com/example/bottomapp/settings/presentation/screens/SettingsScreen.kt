package com.example.bottomapp.settings.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bottomapp.R
import com.example.bottomapp.settings.presentation.components.SettingsCard1
import com.example.bottomapp.settings.presentation.components.SettingsCardOption
import com.example.bottomapp.settings.presentation.components.UserDetailsCard
import com.example.bottomapp.ui.navigation.NavDestination
import com.example.bottomapp.ui.theme.BottomAppTheme
import com.example.bottomapp.ui.theme.Typography

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    userName: String
) {

    val userSettingsCardOptions = listOf(
        SettingsCardOption(
            optionIcon = Icons.Outlined.AccountCircle,
            optionText = "Personal Data",
            optionClicked = {}
        ),
        SettingsCardOption(
            optionIcon = Icons.Outlined.CheckCircle,
            optionText = "Achievement",
            optionClicked = {}
        ),
        SettingsCardOption(
            optionIcon = Icons.Outlined.DateRange,
            optionText = "Workouts Progress",
            optionClicked = {}
        )
    )
    val otherSettingsCardOptions = listOf(
        SettingsCardOption(
            optionIcon = Icons.Outlined.MailOutline,
            optionText = "Contact Us",
            optionClicked = {}
        ),
        SettingsCardOption(
            optionIcon = Icons.Outlined.Lock,
            optionText = "Privacy and Security",
            optionClicked = {}
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = NavDestination.SettingsDestination.title),
                style = Typography.titleLarge

            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            //horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)
                    .clip(CircleShape)
            )

            //Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = userName,
                textAlign = TextAlign.Center,
                style = Typography.labelLarge,
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)

            )

            //Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)
            ) {
                Text(text = "Edit")
            }

        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            ) {
            UserDetailsCard("27","age")
            UserDetailsCard("185","height")
            UserDetailsCard("93","weight")
        }
        SettingsCard1(title = "User", settingsCardOptions = userSettingsCardOptions)
        SettingsCard1(title = "Other", settingsCardOptions = otherSettingsCardOptions)
    }
}




@Preview
@Composable
fun SettingScreenPreview() {
    BottomAppTheme {
        SettingsScreen(
            paddingValues = PaddingValues(),
            userName = "Giuseppe D'Arrò"
        )
    }
}