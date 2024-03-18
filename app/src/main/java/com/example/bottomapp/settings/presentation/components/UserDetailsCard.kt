package com.example.bottomapp.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomapp.ui.theme.BottomAppTheme
import com.example.bottomapp.ui.theme.PurpleGrey80
import com.example.bottomapp.ui.theme.Typography

@Composable
fun RowScope.UserDetailsCard(
    info: String,
    infoType: String
) {
    ElevatedCard(
        modifier = Modifier
            //.fillMaxWidth()
            .weight(1F)
            .padding(14.dp)
            .clip(ShapeDefaults.Large)
            .shadow(20.dp)
            .clickable(onClick = {}),
        elevation = CardDefaults.cardElevation(defaultElevation = 200.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = info,
                color = Color.Gray,
                style = Typography.labelLarge
            )
            Text(
                text = infoType
            )
        }
    }
}

@Preview
@Composable
fun UserDetailsCardPreview() {
    BottomAppTheme {
        Row {
            UserDetailsCard("22","age")
            UserDetailsCard("185","height")
            UserDetailsCard("93","weight")
        }
    }
}