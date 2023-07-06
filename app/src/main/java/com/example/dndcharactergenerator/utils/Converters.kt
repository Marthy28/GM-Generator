package com.example.dndcharactergenerator.utils

import androidx.room.TypeConverter
import com.example.dndcharactergenerator.data.Characteristic
import com.example.dndcharactergenerator.data.Characteristic.Companion.characteristicFromList
import com.example.dndcharactergenerator.data.Characteristic.Companion.toList
import com.example.dndcharactergenerator.data.Race

class Converters {
    @TypeConverter
    fun toList(value: Characteristic?): String? {
        return "coucou"
    }

    @TypeConverter
    fun toCharacteristic(value: String?): Characteristic? {
        return Characteristic(
            15,
            14,
            13,
            12,
            10,
            8, 10
        )
    }

    @TypeConverter
    fun toString(value: Race?): String? {
        return value.toString()
    }

    @TypeConverter
    fun toRace(value: String?): Race? {
        return value?.let { Race.fromString(it) }
    }
}