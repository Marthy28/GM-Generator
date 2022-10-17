package com.example.dndcharactergenerator.ui.newcharacterpage

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.ui.characterdetail.CharacterDetailActivity

@Composable
fun NewCharacterPage() {
    Column() {
        val context = LocalContext.current
        val intent = Intent(context as Activity, CharacterDetailActivity::class.java)

        Text(text = "New character page")
        OutlinedButton(onClick = { //lancer la création de personnage
            val character = CharacterData.createNewCharacter(context)
            intent.putExtra("character", character)
            context.startActivity(intent)
        }) {
            Text("create")
        }
    }
}
