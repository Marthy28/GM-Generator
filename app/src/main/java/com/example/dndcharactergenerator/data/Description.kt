package com.example.dndcharactergenerator.data

data class Description(
    val hair: Hair,
    val physical: List<String>
)

data class Hair(val color: List<String>, val type: List<String>, val length: List<String>)
