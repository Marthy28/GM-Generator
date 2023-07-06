package com.example.dndcharactergenerator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characteristic(
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
    val pv: Int
) : Parcelable {
    companion object {
        private val characteristicsValueList = listOf(
            15,
            14,
            13,
            12,
            10,
            8
        )

        fun generateRandomCarac(): Characteristic {
            val values = characteristicsValueList.shuffled()
            return Characteristic(
                values[0],
                values[1],
                values[2],
                values[3],
                values[4],
                values[5],
                10
            )
        }

        fun returnHp(): Int = 15

        fun Characteristic.toList() = listOf(
            this.strength,
            this.dexterity,
            this.constitution,
            this.intelligence,
            this.wisdom,
            this.charisma,
            this.pv
        )

        fun characteristicFromList(list: List<Int>): Characteristic {
            return Characteristic(
                //TODO faire mieux espèce de gros flemmard
                list[0],
                list[1],
                list[2],
                list[3],
                list[4],
                list[5],
                10
            )
        }
    }
}
