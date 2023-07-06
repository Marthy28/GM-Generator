package com.example.dndcharactergenerator.ui.homepage

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterDataDB
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.component.CharacterCardDetail

@Composable
fun HomePage(navController: NavHostController, viewModel: CharacterDetailViewModel) {
    val context = LocalContext.current
    Column {
        ListOfSavedCharacters(context, navController, viewModel)
    }
}

@Composable
fun ListOfSavedCharacters(
    context: Context,
    navController: NavHostController,
    viewModel: CharacterDetailViewModel
) {
    val openDialog = remember { mutableStateOf(false) }

    //viewModel.getAllChararcter()
    //val characterList = viewModel.allCharacters.observeAsState().value
val characterList : List<CharacterDataDB>? = null

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
            items(items = characterList) { item ->
                //val character = gsonBuilder.fromJson(item, CharacterData::class.java)
                CharacterCardDetail(characterData = item, onClick = {
                    navController.navigate(AppScreens.CharacterDetail.routeWithArgs(item.characterId.toString()))
                }, onDelete = {
                    //selectedCharacter.value = character
                    openDialog.value = true
                    //Log.d("INFO", "character_${character.firstName}_${character.lastName} deleted")
                })
            }
        }
    }
}
