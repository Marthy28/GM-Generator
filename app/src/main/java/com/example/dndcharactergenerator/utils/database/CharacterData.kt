package com.example.dndcharactergenerator.utils.database

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dndcharactergenerator.data.Characteristic
import com.example.dndcharactergenerator.data.CompleteName
import com.example.dndcharactergenerator.data.Race
import com.google.gson.Gson
import kotlin.random.Random
import kotlin.reflect.full.createInstance

private fun readAssetsFile(fileId: Int, context: Context): String =
    context.resources.openRawResource(fileId).bufferedReader().use { it.readText() }

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
            context: Context,
            raceName: Int? = null,
            age: Long? = null
        ): CharacterData {
            val race = raceName?.let { Race.fromStringResource(it) } ?: getRandomRace()
            val fullName = getName(context, race)
            val age = age ?: getRandomAge()
            val characteristic = Characteristic.generateRandomCarac()
            val character = CharacterData(
                firstName = fullName.first,
                lastName = fullName.second,
                race = race,
                age = age,
                characteristic = characteristic,
            )

            return character
        }

        private fun getName(context: Context, race: Race): Pair<String, String> {
            val nameJson = readAssetsFile(race.database, context)
            val names = Gson().fromJson(
                nameJson,
                CompleteName::class.java // TODO ?????? c'est moche ça non ?
            )

            return Pair(names.firstNames.male.random(), names.lastNames.random())
        }

        private fun getRandomRace() = Race::class.sealedSubclasses.random().createInstance()

        private fun getRandomAge() = Random.nextLong(17, 76)
    }

}
