package com.example.dndcharactergenerator.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dndcharactergenerator.data.Character
import com.example.dndcharactergenerator.theme.Dimens

@Composable
fun CharacterCard(car : Character) {
    Card(modifier = Modifier.padding(Dimens.standardPadding)) {
        Row() {
            Text(text = "${car.name} ${car.race}")
        }
    }
}
