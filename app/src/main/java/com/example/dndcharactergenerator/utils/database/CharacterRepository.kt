package com.example.dndcharactergenerator.utils.database

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterRepository(private val characterDao: CharacterDao) {

    val allCharacter = MutableLiveData<List<CharacterData>>()
    val foundCharacter = MutableLiveData<CharacterData>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addCharacter(newCharacter: CharacterData) {
        coroutineScope.launch(Dispatchers.IO) {
            characterDao.addCharacter(newCharacter)
        }
    }

    fun updateCharacterDetails(newCharacter: CharacterData) {
        coroutineScope.launch(Dispatchers.IO) {
            characterDao.updateCharacterDetails(newCharacter)
        }
    }

    fun getCharacter(id: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundCharacter.postValue(characterDao.findCharacterById(id))
        }
    }

    fun getAllCharacters() {
        coroutineScope.launch(Dispatchers.IO) {
            allCharacter.postValue(characterDao.getAllEmployees())
        }
    }

    fun deleteCharacter(characterToDelete: CharacterData) {
        coroutineScope.launch(Dispatchers.IO) {
            characterDao.deleteCharacter(characterToDelete)
        }
    }
}
