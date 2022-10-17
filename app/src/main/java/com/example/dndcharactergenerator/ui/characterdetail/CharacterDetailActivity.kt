package com.example.dndcharactergenerator.ui.characterdetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.theme.MyApplicationTheme

class CharacterDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val character = intent.getParcelableExtra<CharacterData>("character")
        setContent {
            MyApplicationTheme() {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        character?.let { char ->
                            CharacterDetail(char)
                        }
                    }
                }
            }

        }
    }
}
