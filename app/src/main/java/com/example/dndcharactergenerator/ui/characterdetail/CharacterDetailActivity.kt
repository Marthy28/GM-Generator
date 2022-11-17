package com.example.dndcharactergenerator.ui.characterdetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.CharacterDataSerializer
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.utils.SharedPreferencesUtils
import com.google.gson.GsonBuilder


class CharacterDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val character = intent.getParcelableExtra<CharacterData>("character")
        setContent {
            MyApplicationTheme() {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        character?.let { char ->
                            Column() {
                                CharacterDetail(char)
                                OutlinedButton(onClick = {
                                    val gsonBuilder = GsonBuilder().registerTypeAdapter(
                                        CharacterData::class.java,
                                        CharacterDataSerializer()
                                    ).create()
                                    getSharedPreferences(
                                        SharedPreferencesUtils.PREFERENCE_CHARACTERS,
                                        Context.MODE_PRIVATE
                                    ).edit().putString(
                                        "character_${char.firstName}_${char.lastName}",
                                        gsonBuilder.toJson(char)
                                    ).apply()
                                    Log.d("JSON DATA", gsonBuilder.toJson(char))
                                }) {
                                    Text("sauvegarder")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
