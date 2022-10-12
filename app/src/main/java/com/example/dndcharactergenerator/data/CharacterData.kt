package com.example.dndcharactergenerator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class CharacterData(
    val name: String,
    val race: String,
    val age: Int,
    //val gender: Gender,
    //val characteristic: Characteristic? = null,
    //val weapon: Weapon? = null
) : Parcelable
