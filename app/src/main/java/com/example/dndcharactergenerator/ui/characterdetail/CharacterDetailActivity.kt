package com.example.dndcharactergenerator.ui.characterdetail

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dndcharactergenerator.data.CharacterData

class CharacterDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val character = intent.getParcelableExtra<CharacterData>("character")

        setContent {
            character?.let{ char ->
                CharacterDetail(char)
            }

        }
    }
}
