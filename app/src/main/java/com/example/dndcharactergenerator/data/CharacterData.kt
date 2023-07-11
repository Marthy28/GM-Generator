package com.example.dndcharactergenerator.data

import android.content.Context
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import kotlin.random.Random
import kotlin.reflect.full.createInstance

private fun readAssetsFile(fileId: Int, context: Context): String =
    context.resources.openRawResource(fileId).bufferedReader().use { it.readText() }

@Parcelize
@Entity(tableName = "characters")
data class CharacterDataDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
//https://xabaras.medium.com/universally-unique-ids-as-a-primary-key-in-a-room-database-f67a78bdbf4d
    //TODO Créer un objet de type UUID pour incrémenter automatiquement l'id
    @ColumnInfo(name = "characterId")
    var characterId: Long,

    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String,

    @ColumnInfo(name = "race")
    var race: Race? = null,

    @ColumnInfo(name = "characterAge")
    var age: Long,

    @ColumnInfo(name = "characteristics")
    var characteristic: Characteristic? = null,

    @ColumnInfo(name = "characterPhysicalDescription")
    var physicalDescription: String? = "",

    @ColumnInfo(name = "characterBackground")
    var background: String? = "",
) : Parcelable {
    companion object {
        fun createNewCharacter(
            context: Context,
            raceName: Int? = null,
            age: Long? = null
        ): CharacterDataDB {
            val race = raceName?.let { Race.fromStringResource(it) } ?: getRandomRace()
            val fullName = getName(context, race)
            val age = age ?: getRandomAge()
            val characteristic = Characteristic.generateRandomCarac()
            val id = Random.nextInt(0,1000)
            val character =  CharacterDataDB(
                firstName = fullName.first,
                lastName = fullName.second,
                race = race,
                age = age,
                characteristic = characteristic,
                id = id,
                characterId = id.toLong()
            )

            return character
        }

        private fun getName(context: Context, race: Race): Pair<String, String> {
            val nameJson = readAssetsFile(race.database, context)
            val names = Gson().fromJson(
                nameJson,
                CompleteName::class.java
            )

            return Pair(names.firstNames.male.random(), names.lastNames.random())
        }

        private fun getRandomRace() = Race::class.sealedSubclasses.random().createInstance()

        private fun getRandomAge() = Random.nextLong(17, 76)
    }

}
