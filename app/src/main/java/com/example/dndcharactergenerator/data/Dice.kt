package com.example.dndcharactergenerator.data

import androidx.compose.runtime.Composable
import com.example.dndcharactergenerator.ui.component.dices.*

sealed class Dice(val maxValue: Int) {
    data class D4(val maxDiceValue: Int = 4) : Dice(maxDiceValue)
    data class D6(val maxDiceValue: Int = 6) : Dice(maxDiceValue)
    data class D8(val maxDiceValue: Int = 8) : Dice(maxDiceValue)
    data class D10(val maxDiceValue: Int = 10) : Dice(maxDiceValue)
    data class D12(val maxDiceValue: Int = 12) : Dice(maxDiceValue)
    data class D20(val maxDiceValue: Int = 20) : Dice(maxDiceValue)
    data class D100(val maxDiceValue: Int = 100) : Dice(maxDiceValue)
    companion object {
        @Composable
        fun Display(dice: Dice, value: Int) {
            return when (dice) {
                is D10 -> D10Component(value)
                is D100 -> D100Component(value)
                is D20 -> D20Component(value)
                is D4 -> D4Component(value)
                is D8 -> D8Component(value)
                is D12 -> D12Component(value)
                is D6 -> D6Component(value)
            }
        }

        fun toStringLabel(dice: Dice): String {
            return when (dice) {
                is D10 -> "d10"
                is D100 -> "d100"
                is D20 -> "d20"
                is D4 -> "d4"
                is D8 -> "d8"
                is D12 -> "d12"
                is D6 -> "d6"
            }
        }

    }
}



