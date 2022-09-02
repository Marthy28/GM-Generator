package com.example.dndcharactergenerator.data

class Weapon() {
    val name: String
    val damage: String
    val enchant: String? = null
    val description: String

    init {
        name = "weapon"
        damage = "1d6"
        description = "a weapon"
    }
}