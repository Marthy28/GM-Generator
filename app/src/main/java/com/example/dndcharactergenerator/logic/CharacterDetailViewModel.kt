package com.example.dndcharactergenerator.logic

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dndcharactergenerator.data.FirstNames
import com.example.dndcharactergenerator.data.LastNames
import com.example.dndcharactergenerator.data.Race
import com.example.dndcharactergenerator.utils.database.CharacterData
import com.example.dndcharactergenerator.utils.database.CharacterRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlin.reflect.full.createInstance

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
    private fun readAssetsFile(fileId: Int, context: Context): String =
        context.resources.openRawResource(fileId).bufferedReader().use { it.readText() }

    val foundCharacter: LiveData<CharacterData> = characterRepository.foundCharacter
    val allCharacters: LiveData<List<CharacterData>> = characterRepository.allCharacter

    fun saveNewCharacter(character: CharacterData) {
        characterRepository.addCharacter(character)
    }

    fun updateCharacter(character: CharacterData) {
        characterRepository.updateCharacterDetails(character)
        getCharacter(character.id.toString())
    }

    fun getCharacter(id: String) {
        characterRepository.getCharacter(id)
    }

    fun getAllCharacter() {
        characterRepository.getAllCharacters()
    }

    fun deleteCharacter(character: CharacterData) {
        characterRepository.deleteCharacter(character)
    }

    fun createCharacter(
        race: Race,
        age: String,
        firstName: String,
        lastName: String,
        context: Context
    ) = CharacterData.createNewCharacter(
        race,
        if (age.isEmpty()) getRandomAge() else age.toLong(),
        firstName.ifEmpty {
            getRandomFirstNameFromRace(race, context)
        },
        lastName.ifEmpty {
            getRandomLastNameFromRace(race, context)
        }
    )

    //TODO à retravailler
    fun getRandomFirstNameFromRace(race: Race?, context: Context): String {
        val nameJson = readAssetsFile(race?.database ?: getRandomRace().database, context)
        val firstNames = Gson().fromJson(
            nameJson,
            FirstNames::class.java
        )
        return firstNames.firstNames.male.random()
    }

    fun getRandomLastNameFromRace(race: Race?, context: Context): String {
        val nameJson = readAssetsFile(race?.database ?: getRandomRace().database, context)
        val completeName = Gson().fromJson(
            nameJson,
            LastNames::class.java
        )
        return completeName.lastNames.random()
    }

    fun getRandomRace() = Race::class.sealedSubclasses.random().createInstance()
    private fun getRandomAge() = Random.nextLong(17, 76)
}
