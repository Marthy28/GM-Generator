package com.example.dndcharactergenerator.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dndcharactergenerator.data.CharacterDataDB
import com.example.dndcharactergenerator.utils.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

    val foundEmployee: LiveData<CharacterDataDB> = characterRepository.foundCharacter
    val allcharacters: LiveData<List<CharacterDataDB>> = characterRepository.allCharacter

    fun saveNewCharacter(character: CharacterDataDB) {
        characterRepository.addCharacter(character)
    }

    fun updateCharacter(character: CharacterDataDB) {
        characterRepository.updateCharacterDetails(character)
    }

    fun getCharacter(id: String) {
        characterRepository.getCharacter(id)
    }

    fun getAllChararcter() {
        characterRepository.getAllCharacters()
    }
}
