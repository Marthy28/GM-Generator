package com.example.dndcharactergenerator.utils

import androidx.room.TypeConverter
import com.example.dndcharactergenerator.data.Race

class Converters {

    @TypeConverter
    fun toString(value: Race?): String? {
        return value.toString()
    }

    @TypeConverter
    fun toRace(value: String?): Race? {
        return value?.let { Race.fromString(it) }
    }
}