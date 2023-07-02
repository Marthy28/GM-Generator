package com.example.dndcharactergenerator.utils

import androidx.lifecycle.MutableLiveData
import com.example.dndcharactergenerator.data.CharacterDataDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterRepository(private val employeeDao: CharacterDao) {

    val allCharacter = MutableLiveData<List<CharacterDataDB>>()
    val foundCharacter = MutableLiveData<CharacterDataDB>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addCharacter(newEmployee: CharacterDataDB) {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.addEmployee(newEmployee)
        }
    }

    fun updateCharacterDetails(newEmployee: CharacterDataDB) {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.updateEmployeeDetails(newEmployee)
        }
    }

    fun getCharacter(id: String): CharacterDataDB {
        coroutineScope.launch(Dispatchers.IO) {
            foundCharacter.postValue(employeeDao.findEmployeeById(id))
        }
    }

    fun getAllCharacters() {
        coroutineScope.launch(Dispatchers.IO) {
            allCharacter.postValue(employeeDao.getAllEmployees())
        }
    }
}
