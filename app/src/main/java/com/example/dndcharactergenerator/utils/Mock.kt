package com.example.dndcharactergenerator.utils

import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.Dragonborn

class Mock {
    companion object{
        val characterDataMock : CharacterData = CharacterData(
            firstName = "Michel",
            lastName = "Micheline",
            raceName = "Dragonborn",
            race = Dragonborn(),
            age = 45
        )
    }
}
