package com.example.dndcharactergenerator.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.example.dndcharactergenerator.data.CharacterData
import com.google.gson.Gson

class CharacterDataNavType : NavType<CharacterData> (isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): CharacterData? = bundle.getParcelable(key)

    override fun parseValue(value: String): CharacterData {
        return Gson().fromJson(value, CharacterData::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CharacterData) {
        bundle.putParcelable(key, value)
    }
}