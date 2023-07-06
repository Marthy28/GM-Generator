package com.example.dndcharactergenerator.utils

import com.example.dndcharactergenerator.data.CharacterDataDB
import com.example.dndcharactergenerator.data.Dragonborn

class Mock {
    companion object{
        val characterDataMock : CharacterDataDB = CharacterDataDB(
            firstName = "Michel",
            lastName = "Micheline",
            race = Dragonborn(),
            age = 45,
            id = 4,
            characterId = 45,
            characteristic = null,
        )
    }
}
