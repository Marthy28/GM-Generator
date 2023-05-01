package com.example.dndcharactergenerator.ui.homepage

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.CharacterDataDeserializer
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.characterdetail.CharacterDetailActivity
import com.example.dndcharactergenerator.ui.component.CharacterCardDetail
import com.example.dndcharactergenerator.utils.SharedPreferencesUtils
import com.google.gson.GsonBuilder


@Composable
fun HomePage(navController: NavHostController) {
    val context = LocalContext.current
    //val intent = Intent(context as Activity, CharacterDetailActivity::class.java)
    Column() {
        ListOfSavedCharacters(context, navController)
    }
}

@Composable
fun ListOfSavedCharacters(context: Context, navController: NavHostController) {
    val sharedPreferences = context.getSharedPreferences(
        SharedPreferencesUtils.PREFERENCE_CHARACTERS,
        Context.MODE_PRIVATE
    )
    val charactersListJson =
        sharedPreferences.all.map { it.value as String }

    val gsonBuilder = GsonBuilder().registerTypeAdapter(
        CharacterData::class.java,
        CharacterDataDeserializer()
    ).create()

    if (charactersListJson.isEmpty()) {
        Column(modifier = Modifier.padding(Dimens.standardPadding)) {
            Text(stringResource(R.string.character_list_empty))
            Spacer(modifier = Modifier.height(Dimens.standardPadding))
            OutlinedButton(onClick = {
                navController.navigate("newCharacter") {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                }
            })
            {
                Text(text = stringResource(id = R.string.create_new_character))
            }
        }
    } else {
        LazyColumn(modifier = Modifier.padding(Dimens.standardPadding)) {
            items(items = charactersListJson) { item ->
                val character = gsonBuilder.fromJson(item, CharacterData::class.java)
                CharacterCardDetail(characterData = character, onClick = {
                    val intent = Intent(context as Activity, CharacterDetailActivity::class.java)
                    intent.putExtra("character", character)
                    context.startActivity(intent)
                })
            }
        }
    }
}