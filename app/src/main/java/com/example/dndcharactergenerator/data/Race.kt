package com.example.dndcharactergenerator.data

import android.os.Parcelable
import com.example.dndcharactergenerator.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Race : Parcelable {
    DRAGONBORN {
        override fun getName() : Int = R.string.race_dragonborn
        override fun getDatabase() : Int = R.raw.dragonborn_names
    },
    ELF {
        override fun getName() : Int = R.string.race_elf
        override fun getDatabase() : Int = R.raw.elf_names
    },
    DWARF {
        override fun getName() : Int = R.string.race_dwarf
        override fun getDatabase() : Int = R.raw.dwarf_names
    };
    abstract fun getDatabase() :Int
    abstract fun getName() : Int
}
