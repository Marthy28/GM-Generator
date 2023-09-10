package com.example.dndcharactergenerator.ui.dicepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.data.Dice
import com.example.dndcharactergenerator.data.Dice.Companion.toStringLabel
import com.example.dndcharactergenerator.theme.Dimens

import kotlin.random.Random

val dices = listOf<Dice>(
    Dice.D4(),
    Dice.D6(),
    Dice.D8(),
    Dice.D10(),
    Dice.D20(),
    Dice.D100()
)

//https://stackoverflow.com/questions/68603329/segment-controls-jetpack-compose
@Composable
fun DicePage(navController: NavHostController) {
    val diceType = remember { mutableStateOf(0) }
    val result = remember { mutableStateOf(0) }
    val display = remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        SegmentedButton(
            dices.map { toStringLabel(it) }, modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.standardPadding)
        ) {
            display.value = false
            diceType.value = it
        }
        Button(onClick = {
            result.value = Random.nextInt(1, dices[diceType.value].maxValue) + 1
            display.value = true
        }) {
            Text(text = "Lancer le dé")
        }

        if (display.value)
            Dice.Display(dice = dices[diceType.value], value = result.value)
    }
}
