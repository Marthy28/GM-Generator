package com.example.dndcharactergenerator.utils

import com.example.dndcharactergenerator.data.Dragonborn
import com.example.dndcharactergenerator.utils.database.CharacterData

class Mock {
    companion object{
        val characterDataMock : CharacterData = CharacterData(
            firstName = "Michel",
            lastName = "Micheline",
            race = Dragonborn(),
            age = 45,
            id = 4,
        )
    }
}
