package com.example.dndcharactergenerator.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dndcharactergenerator.utils.database.CharacterData
import com.example.dndcharactergenerator.utils.database.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

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
}
