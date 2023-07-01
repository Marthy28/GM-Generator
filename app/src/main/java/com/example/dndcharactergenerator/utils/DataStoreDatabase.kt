package com.example.dndcharactergenerator.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.dndcharactergenerator.data.CharacterData

object DataStoreDatabase {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    fun addCharacter (characterData: CharacterData, context: Context) {
         val CHARACTER_DATABASE = stringPreferencesKey("character_database")
        //val characterDatabaseFlow: Flow<List<CharacterData>> = context.dataStore.data.map {preferences -> "bonjour" }
    }
} //https://developer.android.com/topic/libraries/architecture/datastore?hl=fr