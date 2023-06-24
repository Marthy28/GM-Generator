package com.example.dndcharactergenerator.ui.characterdetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        val editable = intent.getBooleanExtra("editable", false)

        setContent {
            MyApplicationTheme() {
                Scaffold {
                    var editMode by remember { mutableStateOf(false) }
                    Column(modifier = Modifier.padding(it)) {
                        TopBar(editMode, onClick = { editMode = !editMode })
                        character?.let { char ->
                            if (!editMode) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.padding(Dimens.standardPadding)
                                ) {
                                    CharacterDetail(char)
                                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                                    if (!editable) OutlinedButton(
                                        onClick = { saveCharacter(char) },
                                    ) {
                                        Text(getString(R.string.Save))
                                    }
                                }
                            } else {
                                EditableMode(char = char, onSave = { editMode = false })
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun EditableMode(char: CharacterData, onSave: () -> Unit) {
        var firstName by remember { mutableStateOf(char.firstName) }
        var lastName by remember { mutableStateOf(char.lastName) }
        var age by remember { mutableStateOf(char.age.toString()) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.standardPadding)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.halfPadding)) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(text = "first name") },
                    placeholder = {
                        Text(
                            text =
                            "Entrez le prénom du personnage",
                            fontSize = 12.sp,
                        )
                    })
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text(text = "last name") },
                    placeholder = {
                        Text(
                            text =
                            "Entrez le nom du personnage",
                            fontSize = 12.sp,
                        )
                    })
            }
            OutlinedTextField(value = age,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { age = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Age") },
                placeholder = {
                    Text(
                        text =
                        "Entrez l'âge du personnage (par défaut aléatoire)",
                        fontSize = 12.sp,
                    )
                })
            Text(text = "mode édition coucou")
            Text(text = "Ajouter une description physique")
            Text(text = "Ajouter une histoire")
            OutlinedButton(onClick = {
                CharacterData(
                    firstName = firstName,
                    lastName = lastName,
                    age = age.toInt(),
                    race = char.race,
                    raceName = char.raceName
                )
                saveCharacter(char)
                onSave()
            }) {
                Text(text = getString(R.string.Save))
                // if validate
            }
        }
    }

    private fun saveCharacter(char: CharacterData) {
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
    }

    @Composable
    private fun TopBar(editMode: Boolean, onClick: () -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { finish() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = {
                onClick()
            }) {
                Icon(
                    if (!editMode) Icons.Filled.Edit else Icons.Filled.Close,
                    contentDescription = "Edit",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

}
