package com.example.dndcharactergenerator.data

import android.os.Parcelable
import com.example.dndcharactergenerator.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Race : Parcelable {
    DRAGONBORN, ELF, DWARF
}

val raceFile = mapOf(
    Race.DRAGONBORN to Pair(R.raw.dragonborn_names, R.string.race_dragonborn),
    Race.ELF to Pair(R.raw.elf_names, R.string.race_elf),
    Race.DWARF to Pair(R.raw.dwarf_names, R.string.race_dwarf)
)
