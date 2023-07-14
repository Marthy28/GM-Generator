package com.example.dndcharactergenerator.ui.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.component.CharacterCardDetail
import com.example.dndcharactergenerator.ui.component.SimpleAlertDialog
import com.example.dndcharactergenerator.utils.database.CharacterData

@Composable
fun HomePage(
    navController: NavHostController,
    viewModel: CharacterDetailViewModel,
    showSnackbar: (String, SnackbarDuration) -> Unit
) {
    Column {
        ListOfSavedCharacters(navController, viewModel, showSnackbar)
    }
}

@Composable
fun ListOfSavedCharacters(
    navController: NavHostController,
    viewModel: CharacterDetailViewModel,
    showSnackbar: (String, SnackbarDuration) -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }
    val characterToDelete = remember { mutableStateOf<CharacterData?>(null) }

    viewModel.getAllChararcter()
    val characterList = viewModel.allCharacters.observeAsState().value

    if (openDialog.value) {
        SimpleAlertDialog(
            onDismiss = { openDialog.value = false },
            onValidate = {
                characterToDelete.value?.let {
                    viewModel.deleteCharacter(it)
                    showSnackbar("Personnage Supprimé", SnackbarDuration.Long)
                }
                openDialog.value = false
            })
    }

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
                CharacterCardDetail(characterData = item, onClick = {
                    //navController.navigate(AppScreens.CharacterDetail.routeWithArgs(item.id.toString()))
                    val route = AppScreens.CharacterDetail.routeWithArgs(item.id.toString())
                    navController.navigate(route)
                }, onDelete = {
                    characterToDelete.value = item
                    openDialog.value = true
                })
            }
        }
    }
}
