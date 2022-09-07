package com.example.dndcharactergenerator.data

import kotlin.random.Random

class Character(generatedName: String) {
    val name: String
    val race: String
    val age: Int
    val gender: Gender
    val heroClass: String? = null
    val characteristic: Characteristic
    val weapon: Weapon

    init {
        //générer un nom, âge, etc
        name = generatedName
        age = generateAge()
        race = "humain"
        gender = Gender.MALE
        characteristic = Characteristic()
        weapon = Weapon()
    }

    private fun generateAge(): Int = Random.nextInt(20, 60)
}
