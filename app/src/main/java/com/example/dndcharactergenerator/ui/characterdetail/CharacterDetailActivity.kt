package com.example.dndcharactergenerator.ui.characterdetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.CharacterDataSerializer
import com.example.dndcharactergenerator.theme.Dimens
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
                        IconButton(onClick = { finish() }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        character?.let { char ->
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(Dimens.standardPadding)
                            ) {
                                CharacterDetail(char)
                                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                                OutlinedButton(
                                    onClick = {
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
                                    },
                                ) {
                                    Text(getString(R.string.Save))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
