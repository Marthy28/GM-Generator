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
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.component.dices.D10
import com.example.dndcharactergenerator.ui.component.dices.D100
import com.example.dndcharactergenerator.ui.component.dices.D12
import com.example.dndcharactergenerator.ui.component.dices.D20
import com.example.dndcharactergenerator.ui.component.dices.D4
import com.example.dndcharactergenerator.ui.component.dices.D6
import com.example.dndcharactergenerator.ui.component.dices.D8
import kotlin.random.Random

val dices: Map<Int, Int> = mapOf(
    0 to 4,
    1 to 6,
    2 to 8,
    3 to 10,
    4 to 20,
    5 to 100
)

//https://stackoverflow.com/questions/68603329/segment-controls-jetpack-compose
@Composable
fun DicePage(navController: NavHostController) {
    val diceType = remember { mutableStateOf(0) }
    val result = remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        SegmentedButton(
            listOf("d4", "d6", "d8", "d10", "d20", "d100"), modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.standardPadding)
        ) {
            diceType.value = it
        }
        Button(onClick = { result.value = Random.nextInt(1, dices[diceType.value]!! + 1) }) {
            Text(text = "Lancer le dé")
        }
        if (result.value != 0)
            Text(
                text = result.value.toString()
            )
        D20(value = 20)
        D8(value = 8)
        D6(value = 6)
        D4(value = 4)
        D12(value = 12)
        D10(value = 10)
        D100(value = 100)
    }
}
