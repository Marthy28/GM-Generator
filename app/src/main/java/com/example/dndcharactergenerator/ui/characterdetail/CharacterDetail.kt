package com.example.dndcharactergenerator.ui.characterdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.Race
import com.example.dndcharactergenerator.data.raceFile
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
                    value = stringResource(raceFile[character.race]!!.second)
                )
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                DescriptionAndValue(
                    description = stringResource(R.string.age_description),
                    value = character.age.toString()
                )
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
        "Michel",
        "Micheline",
        Race.DRAGONBORN,
        45
    )
) {
    CharacterDetail(character = character)
}
