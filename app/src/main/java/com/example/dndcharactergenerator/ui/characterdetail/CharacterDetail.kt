package com.example.dndcharactergenerator.ui.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.Characteristic
import com.example.dndcharactergenerator.data.Characteristic.Companion.toList
import com.example.dndcharactergenerator.data.Dragonborn
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.utils.database.CharacterData

@Composable
fun CharacterDetail(
    character: CharacterData,
    editableClick: () -> Unit = {},
    editMode: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(Dimens.standardPadding)) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "${character.firstName} ${character.lastName}",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(0.90f)
                )
                if (editMode) {
                    IconButton(
                        modifier = Modifier.weight(0.10f),
                        onClick = {
                            editableClick()
                        }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(Dimens.standardPadding))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                //TODO A VOIR PARCE QUE CA VA PLANTER
                /*DescriptionAndValue(
                    description = stringResource(R.string.race_description),
                    value = stringResource(character.race.name)
                )*/
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                DescriptionAndValue(
                    description = stringResource(R.string.age_description),
                    value = character.age.toString()
                )
            }
            character.characteristic?.let {
                CharacteristicGrid(it)
            }

            if (!character.physicalDescription.isNullOrEmpty()) {
                ExtraSection(
                    title = "Description Physique",
                    content = character.physicalDescription
                )
            }

            if (!character.background.isNullOrEmpty()) {
                ExtraSection(title = "Background", content = character.background)
            }
        }

    }

}

@Composable
private fun CharacteristicGrid(characteristic: Characteristic) {
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
                Text(text = charac, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(Dimens.halfPadding))
                Text(text = "${characteristicsList[index]}")
                Spacer(modifier = Modifier.height(Dimens.halfPadding))
                Text(
                    text = when (characteristicsList[index]) {
                        in 2..3 -> "-4"
                        in 4..5 -> "-3"
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

@Composable
fun ExtraSection(title: String, content: String) {
    Column (modifier = Modifier.padding(top = Dimens.standardPadding)){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Divider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = Dimens.halfPadding)
            )
        }
        Spacer(modifier = Modifier.height(Dimens.halfPadding))
        Text(text = content, fontSize = 12.sp)
    }
}

@Preview
@Composable
fun ShowCharacterDetails(
    character: CharacterData = CharacterData(
        firstName = "Michel",
        lastName = "Micheline",
        race = Dragonborn(),
        age = 45,
        id = 1,
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
        
