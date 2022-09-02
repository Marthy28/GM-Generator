package com.example.dndcharactergenerator.data

import kotlin.random.Random

class Characteristic() {
// créer une map avec les caracs pour éviter de copier coller le code inutilement

    val strength: Int
    val dexterity: Int
    val consitution: Int
    val intelligence: Int
    val wisdom: Int
    val charisma: Int
    val PV: Int

    init {
        strength = generateCarac()
        dexterity = generateCarac()
        consitution = generateCarac()
        intelligence = generateCarac()
        wisdom = generateCarac()
        charisma = generateCarac()
        PV = consitution
    }

    fun generateCarac(): Int = Random.nextInt(0, 12)
}