package com.example.dndcharactergenerator.data

import kotlin.random.Random

class Character(generatedName: String) {
    private val name: String
    private val race: String
    private val age: Int
    private val gender: Gender
    private val heroClass: String? = null
    private val characteristic: Characteristic
    private val weapon: Weapon

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