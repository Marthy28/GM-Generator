package com.example.dndcharactergenerator.ui.characterdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.Characteristic
import com.example.dndcharactergenerator.data.Characteristic.Companion.toList
import com.example.dndcharactergenerator.data.Dragonborn
import com.example.dndcharactergenerator.theme.Dimens

@Composable
fun CharacterDetail(character: CharacterData) {
    Card(
        modifier = Modifier
            .padding(Dimens.standardPadding)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(Dimens.standardPadding)) {
            Text(
                text = "${character.firstName} ${character.lastName}",
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(Dimens.standardPadding))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                DescriptionAndValue(
                    description = stringResource(R.string.race_description),
                    value = stringResource(character.race.name)
                )
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                DescriptionAndValue(
                    description = stringResource(R.string.age_description),
                    value = character.age.toString()
                )
            }
            character.characteristic?.let {
                CharacteristicGrid(it)
            }
        }

    }

}

@Composable
fun CharacteristicGrid(characteristic: Characteristic) {
    val characteristicsString = listOf("STR", "DEX", "CON", "INT", "SAG", "CHA", "HP")
    val characteristicsList = characteristic.toList()
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.halfPadding)
    ) {
        itemsIndexed(characteristicsString) { index, charac ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = charac)
                Spacer(modifier = Modifier.height(Dimens.halfPadding))
                Text(text = "${characteristicsList[index]}")
                Spacer(modifier = Modifier.height(Dimens.halfPadding))
                Text(
                    text = when (characteristicsList[index]) {
                        in 6..7 -> "-2"
                        in 8..9 -> "-1"
                        in 12..13 -> "+1"
                        in 14..15 -> "+2"
                        in 16..17 -> "+3"
                        in 18..19 -> "+4"
                        else -> "/"
                    }
                )

            }
            if (index != characteristicsList.lastIndex) {
                Spacer(modifier = Modifier.width(Dimens.halfPadding))
                Divider(
                    color = Color.Gray.copy(alpha = 0.4F),
                    modifier = Modifier
                        .width(1.dp)
                        .height(Dimens.triplePadding)
                )
                Spacer(modifier = Modifier.width(Dimens.halfPadding))
            }
        }
    }
}


@Composable
fun DescriptionAndValue(description: String, value: String) {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append(description)
        }
        append(" $value")
    })
}

@Preview
@Composable
fun ShowCharacterDetails(
    character: CharacterData = CharacterData(
        firstName = "Michel",
        lastName = "Micheline",
        raceName = "Dragonbon",
        race = Dragonborn(),
        age = 45
    )
) {
    CharacterDetail(character = character)
}

@Preview
@Composable
fun ShowCharacteristicsGrid(
    characteristic: Characteristic = Characteristic(
        12,
        12,
        12,
        12,
        12,
        12,
        12
    )
) {
    CharacteristicGrid(characteristic = characteristic)
}
        
