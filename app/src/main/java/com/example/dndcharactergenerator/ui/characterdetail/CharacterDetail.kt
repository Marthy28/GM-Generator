package com.example.dndcharactergenerator.ui.characterdetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.dndcharactergenerator.data.CharacterData

@Composable
fun CharacterDetail(character : CharacterData) {
    Text(text = character.name)
}
