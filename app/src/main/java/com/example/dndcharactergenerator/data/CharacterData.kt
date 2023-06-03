package com.example.dndcharactergenerator.data

import android.content.Context
import android.os.Parcelable
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
    //val gender: Gender,
    val characteristic: Characteristic? = null,
    //val weapon: Weapon? = null
) : Parcelable {
    @IgnoredOnParcel
    val pagedList: Race = race

    companion object {
        fun createNewCharacter(
            context: Context,
            raceName: Int? = null,
            age: Int? = null
        ): CharacterData {
            val race = raceName?.let { Race.fromStringResource(it) } ?: getRandomRace()
            val raceName = race.javaClass.simpleName
            val fullName = getName(context, race)
            val age = age ?: getRandomAge()
            val characteristic = Characteristic.generateRandomCarac()
            return CharacterData(
                fullName.first,
                fullName.second,
                race,
                raceName,
                age,
                characteristic
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

        private fun getRandomAge() = Random.nextInt(17, 76)
    }
}
