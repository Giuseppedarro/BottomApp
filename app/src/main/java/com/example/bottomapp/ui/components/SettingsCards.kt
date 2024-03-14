package com.example.bottomapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomapp.ui.theme.BottomAppTheme
import com.example.bottomapp.ui.theme.Typography


data class SettingsCardOption(
    val optionIcon: ImageVector,
    val optionText: String,
    val optionClicked: () -> Unit
)

@Composable
fun ColumnScope.SettingsCard1(
    title: String,
    settingsCardOptions: List<SettingsCardOption>
) {


    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(ShapeDefaults.Large)
            .shadow(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 200.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = title,
                    style = Typography.titleLarge
                )

            }
            settingsCardOptions.forEach {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable( onClick = it.optionClicked)
                ) {
                    Icon(imageVector = it.optionIcon, contentDescription = null)
                    Text(
                        text =  it.optionText,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = null
                    )

                }
            }


        }
    }

}

@Preview
@Composable
fun SettingCardsPreview() {
    BottomAppTheme {
        Column {
            SettingsCard1(
                title = "User",
                settingsCardOptions = listOf(
                    SettingsCardOption(
                        Icons.Outlined.AccountCircle,
                        optionText = "Personal data",
                        optionClicked = {}
                    )
                )
            )
        }
    }
}