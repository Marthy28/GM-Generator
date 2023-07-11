package com.example.dndcharactergenerator.utils.database

import androidx.room.*
import com.example.dndcharactergenerator.data.CharacterDataDB

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character: CharacterDataDB)

    @Query("SELECT * FROM characters WHERE characterId = :charId")
    fun findCharacterById(charId: String): CharacterDataDB

    @Query("SELECT * FROM characters")
    fun getAllEmployees(): List<CharacterDataDB>

    @Update
    suspend fun updateCharacterDetails(character: CharacterDataDB)

    @Delete
    suspend fun deleteCharacter(character: CharacterDataDB)
}
