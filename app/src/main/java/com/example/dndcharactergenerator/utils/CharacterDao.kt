package com.example.dndcharactergenerator.utils

import androidx.room.*
import com.example.dndcharactergenerator.data.CharacterDataDB

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployee(employee: CharacterDataDB)

    @Query("SELECT * FROM characters WHERE characterId = :empId")
    fun findEmployeeById(empId: String): CharacterDataDB

    @Query("SELECT * FROM characters")
    fun getAllEmployees(): List<CharacterDataDB>

    @Update
    suspend fun updateEmployeeDetails(employee: CharacterDataDB)

    @Delete
    suspend fun deleteEmployee(employee: CharacterDataDB)
}
