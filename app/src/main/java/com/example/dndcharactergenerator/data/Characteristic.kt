package com.example.dndcharactergenerator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Characteristic(
    val strength: Int,
    val dexterity: Int,
    val consitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
    val PV: Int) :Parcelable
{
    /*init {
        strength = generateCarac()
        dexterity = generateCarac()
        consitution = generateCarac()
        intelligence = generateCarac()
        wisdom = generateCarac()
        charisma = generateCarac()
        PV = consitution
    }*/

    fun generateCarac(): Int = Random.nextInt(0, 12)
}