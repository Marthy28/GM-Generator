package com.example.dndcharactergenerator.data

import android.os.Parcelable
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.dndcharactergenerator.R
import kotlinx.parcelize.Parcelize
import kotlin.reflect.full.createInstance

@Parcelize
sealed class Race(val name: Int, val database: Int) : Parcelable {

    companion object {
        fun fromString(name: String) =
            Race::class.sealedSubclasses.firstOrNull { it.simpleName == name }?.createInstance()
                ?: Dragonborn()
    }
}

@Parcelize
data class Dragonborn(
    @StringRes
    val dragonbornName: Int = R.string.race_dragonborn,
    @RawRes
    val dragonbornDatabase: Int = R.raw.dragonborn_names
) : Race(dragonbornName, dragonbornDatabase)

@Parcelize
data class Elf(
    @StringRes
    val elfName: Int = R.string.race_elf,
    @RawRes
    val elfDatabase: Int = R.raw.elf_names
) : Race(elfName, elfDatabase)

@Parcelize
data class Dwarf(
    @StringRes
    val dwarfName: Int = R.string.race_dwarf,
    @RawRes
    val dwarfDatabase: Int = R.raw.dwarf_names
) : Race(dwarfName, dwarfDatabase)

@Parcelize
sealed class Race2(val name: String) : Parcelable {
    data class Elf(val nameElf: String) : Race2(nameElf)
}

fun Race2.getDataBase() = when (this) {
    is Race2.Elf -> R.raw.elf_names
}
