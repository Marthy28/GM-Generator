package com.example.dndcharactergenerator.ui.homepage

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.CharacterDataDeserializer
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.component.CharacterCardDetail
import com.example.dndcharactergenerator.ui.component.SimpleAlertDialog
import com.example.dndcharactergenerator.utils.SharedPreferencesUtils
import com.google.gson.GsonBuilder

@Composable
fun HomePage(navController: NavHostController, viewModel: CharacterDetailViewModel) {
    val context = LocalContext.current
    Column {
        ListOfSavedCharacters(context, navController)
    }
}

@Composable
fun ListOfSavedCharacters(
    context: Context,
    navController: NavHostController,
    viewModel: CharacterDetailViewModel
) {
    val openDialog = remember { mutableStateOf(false) }
    val selectedCharacter = remember {
        mutableStateOf<CharacterData?>(null)
    }

    viewModel.getAllChararcter()
    val characterList = viewModel.allcharacters.observeAsState().value


    val gsonBuilder = GsonBuilder().registerTypeAdapter(
        CharacterData::class.java,
        CharacterDataDeserializer()
    ).create()

    /*if (openDialog.value) {
        SimpleAlertDialog(
            onDismiss = { openDialog.value = false },
            onValidate = {
                selectedCharacter.value?.let {
                    sharedPreferences.edit().remove(
                        it.uid,
                    ).apply()
                }
                charactersListJson = sharedPreferences.all.map { it.value as String }
                openDialog.value = false
            })
    }*/

    if (characterList.isNullOrEmpty()) {
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
                    navController.navigate(AppScreens.CharacterDetail.routeWithArgs(character.uid.toString()))
                }, onDelete = {
                    selectedCharacter.value = character
                    openDialog.value = true
                    Log.d("INFO", "character_${character.firstName}_${character.lastName} deleted")
                })
            }
        }
    }
}
