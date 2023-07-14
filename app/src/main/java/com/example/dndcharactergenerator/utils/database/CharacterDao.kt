package com.example.dndcharactergenerator.utils.database

import androidx.room.*

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character: CharacterData)

    @Query("SELECT * FROM characters WHERE charId = :charId")
    fun findCharacterById(charId: String): CharacterData

    @Query("SELECT * FROM characters")
    fun getAllEmployees(): List<CharacterData>

    @Update
    suspend fun updateCharacterDetails(character: CharacterData)

    @Delete
    suspend fun deleteCharacter(character: CharacterData)
}
