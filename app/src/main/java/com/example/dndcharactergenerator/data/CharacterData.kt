package com.example.dndcharactergenerator.data

import android.content.Context
import android.content.res.AssetManager
import android.os.Parcelable
import com.example.dndcharactergenerator.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

private fun AssetManager.readAssetsFile(fileId: Int, context: Context): String =
    context.resources.openRawResource(fileId).bufferedReader().use { it.readText() }

@Parcelize
data class CharacterData(
    val firstName: String,
    val lastName: String,
    val race: Race,
    val age: Int,
    //val gender: Gender,
    //val characteristic: Characteristic? = null,
    //val weapon: Weapon? = null
) : Parcelable {
    companion object {
        fun createNewCharacter(context: Context): CharacterData {
            val fullName = getName(context)
            val race = Race.DRAGONBORN
            val age = 45
            return CharacterData(fullName.first, fullName.second, race, age)
        }

        fun getName(context: Context): Pair<String, String> {
            val nameJson = context.assets.readAssetsFile(R.raw.dragonborn_names, context)
            val names = Gson().fromJson(
                nameJson,
                CompleteName::class.java
            )

            return Pair(names.firstNames.male.random(), names.lastNames.random())
        }
    }


}
