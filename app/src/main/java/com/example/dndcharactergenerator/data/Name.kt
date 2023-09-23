package com.example.dndcharactergenerator.data

data class FirstNames(
    var firstNames: FirstGenderedNames,
)

data class FirstGenderedNames(
    var male: List<String>,
    var female: List<String>
)

data class LastNames(
    var lastNames: List<String>
)
