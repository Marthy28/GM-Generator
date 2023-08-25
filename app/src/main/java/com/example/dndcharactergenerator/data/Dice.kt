package com.example.dndcharactergenerator.data

import androidx.compose.runtime.Composable
import com.example.dndcharactergenerator.ui.component.dices.*

sealed class Dice(val maxValue: Int) {

    data class D4(val maxDiceValue: Int) : Dice(maxValue = maxDiceValue)

    data class D6(val maxDiceValue: Int) : Dice(maxValue = maxDiceValue)

    data class D8(val maxDiceValue: Int) : Dice(maxValue = maxDiceValue)

    data class D10(val maxDiceValue: Int) : Dice(maxValue = maxDiceValue)

    data class D12(val maxDiceValue: Int) : Dice(maxValue = maxDiceValue)

    data class D20(val maxDiceValue: Int) : Dice(maxValue = maxDiceValue)

    data class D100(val maxDiceValue: Int) : Dice(maxValue = maxDiceValue)
}

@Composable
fun Display(dice: Dice, value: Int) {
    return when (dice) {
        is Dice.D10 -> D10(value)
        is Dice.D100 -> D100(value)
        is Dice.D20 -> D20(value)
        is Dice.D4 -> D4(value)
        is Dice.D8 -> D8(value)
        is Dice.D12 -> D12(value)
        is Dice.D6 -> D6(value)
    }
}

