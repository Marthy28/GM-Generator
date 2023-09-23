package com.example.dndcharactergenerator.utils.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dndcharactergenerator.data.Characteristic
import com.example.dndcharactergenerator.data.Race

@Entity(tableName = "characters")
data class CharacterData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "charId")
    val id: Int = 0,
//https://xabaras.medium.com/universally-unique-ids-as-a-primary-key-in-a-room-database-f67a78bdbf4d
    //TODO Créer un objet de type UUID pour incrémenter automatiquement l'id
    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "lastName")
    val lastName: String,

    @ColumnInfo(name = "race")
    val race: Race? = null,

    @ColumnInfo(name = "characterAge")
    val age: Long,

    @ColumnInfo(name = "characteristics")
    val characteristic: Characteristic? = null,

    @ColumnInfo(name = "characterPhysicalDescription")
    val physicalDescription: String? = "",

    @ColumnInfo(name = "characterBackground")
    val background: String? = "",
) {

    companion object {
        fun createNewCharacter(
            race: Race,
            age: Long,
            firstName: String,
            lastName: String,
        ): CharacterData {
            val characteristic = Characteristic.generateRandomCarac()
            return CharacterData(
                firstName = firstName,
                lastName = lastName,
                race = race,
                age = age,
                characteristic = characteristic,
            )
        }
    }

}
