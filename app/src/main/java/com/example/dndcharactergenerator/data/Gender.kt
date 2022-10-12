package com.example.dndcharactergenerator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Gender : Parcelable {
    MALE, FEMALE, UNDEFINED
}