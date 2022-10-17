package com.example.dndcharactergenerator.data

data class CompleteName(
    var firstNames: FirstNames,
    var lastNames: List<String>
    )


data class FirstNames(
    var male: List<String>,
    var female: List<String>
)