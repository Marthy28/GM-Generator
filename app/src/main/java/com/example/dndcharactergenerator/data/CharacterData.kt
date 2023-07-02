package com.example.dndcharactergenerator.data

import android.content.Context
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlin.random.Random
import kotlin.reflect.full.createInstance

private fun readAssetsFile(fileId: Int, context: Context): String =
    context.resources.openRawResource(fileId).bufferedReader().use { it.readText() }

@Parcelize
data class CharacterData(
    val firstName: String,
    val lastName: String,
    @Expose(serialize = false)
    val race: Race,
    val raceName: String,
    val age: Int,
    val characteristic: Characteristic? = null,
    val physicalDescription: String? = "",
    val background: String? = "",
    var uid: String? = "",
    //val weapon: Weapon? = null
) : Parcelable

@Parcelize
@Entity(tableName = "characters")
data class CharacterDataDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "characterId")
    var characterId: Long,

    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "lastName")
    val lastName: String,

    @Expose(serialize = false)
    val race: Race,

    @ColumnInfo(name = "raceName")
    val raceName: String,

    @ColumnInfo(name = "characterAge")
    val age: Long,

    @ColumnInfo(name = "characteristics")
    val characteristic: Characteristic? = null,

    @ColumnInfo(name = "characterPhysicalDescription")
    val physicalDescription: String? = "",

    @ColumnInfo(name = "characterBackground")
    val background: String? = "",
) : Parcelable {
    companion object {
        fun createNewCharacter(
            context: Context,
            raceName: Int? = null,
            age: Long? = null
        ): CharacterDataDB {
            val race = raceName?.let { Race.fromStringResource(it) } ?: getRandomRace()
            val raceName = race.javaClass.simpleName
            val fullName = getName(context, race)
            val age = age ?: getRandomAge()
            val characteristic = Characteristic.generateRandomCarac()
            return CharacterDataDB(
                firstName = fullName.first,
                lastName = fullName.second,
                race = race,
                raceName = raceName,
                age = age,
                characteristic = characteristic,
                characterId = 1,
                id = 1
            )
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
