package com.example.dndcharactergenerator.data

sealed class Dice (val maxValue: Int){

    data class D4 (val maxDiceValue:Int) : Dice(maxValue = maxDiceValue)

    data class D8 (val maxDiceValue:Int) : Dice(maxValue = maxDiceValue)

    data class D10 (val maxDiceValue:Int) : Dice(maxValue = maxDiceValue)

    data class D20 (val maxDiceValue:Int) : Dice(maxValue = maxDiceValue)

    data class D100(val maxDiceValue:Int) : Dice(maxValue = maxDiceValue)
}

