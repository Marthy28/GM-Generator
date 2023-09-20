package com.example.dndcharactergenerator.ui.characterdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.theme.MyApplicationTheme

@Composable
fun CharacterDetailPage(
    navController: NavHostController,
    characterId: String?,
    viewModel: CharacterDetailViewModel
) {
    viewModel.getCharacter(characterId!!)
    val selectedCharacter = viewModel.foundCharacter.observeAsState().value

    MyApplicationTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(Dimens.standardPadding)
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
            selectedCharacter?.let {
                CharacterDetail(
                    selectedCharacter,
                    editableClick = {
                        val route = AppScreens.CharacterDetailEdit.routeWithArgs(characterId)
                        navController.navigate(route) {
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                    },
                    editMode = true
                )
            }

        }
    }
}