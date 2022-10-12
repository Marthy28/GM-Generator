package com.example.dndcharactergenerator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weapon(
    val name: String = "épée de la mort",
    val damage: String = "1d6",
    val enchant: String? = null,
    val description: String = "une belle épée"
) : Parcelable {


}