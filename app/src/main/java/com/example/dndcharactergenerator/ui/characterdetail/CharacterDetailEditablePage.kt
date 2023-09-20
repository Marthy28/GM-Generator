package com.example.dndcharactergenerator.ui.characterdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.utils.database.CharacterData

@Composable
fun CharacterDetailEditablePage(
    navController: NavHostController,
    characterId: String?,
    viewModel: CharacterDetailViewModel
) {
    viewModel.getCharacter(characterId!!)
    val selectedCharacter = viewModel.foundCharacter.observeAsState().value

    MyApplicationTheme() {
        Column() {
            selectedCharacter?.let { char ->
                IconButton(onClick = {
                    val route = AppScreens.CharacterDetail.routeWithArgs(characterId)
                    navController.navigate(route) {
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
                EditableMode(
                    char = char,
                    onClick =
                    {
                        val route = AppScreens.CharacterDetail.routeWithArgs(characterId)
                        navController.navigate(route) {
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
private fun EditableMode(
    char: CharacterData,
    onClick: () -> Unit,
    viewModel: CharacterDetailViewModel
) {
    var firstName by remember { mutableStateOf(char.firstName) }
    var lastName by remember { mutableStateOf(char.lastName) }
    var age by remember { mutableStateOf(char.age.toString()) }
    //PAS MIEUX TROUVÉ

    //Background
    var isBackground by remember {
        mutableStateOf(!char.background.isNullOrBlank())
    }

    var background by remember {
        mutableStateOf(
            char.background ?: ""
        )
    }

    //Physical description
    var isPhysicalDescription by remember {
        mutableStateOf(
            !char.physicalDescription.isNullOrBlank()
        )
    }
    var physicalDescription by remember {
        mutableStateOf(
            char.physicalDescription ?: ""
        )
    }

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
                label = { Text(text = stringResource(R.string.first_name)) },
                placeholder = {
                    Text(
                        text =
                        stringResource(R.string.enter_character_name),
                        fontSize = 12.sp,
                    )
                })
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = stringResource(R.string.last_name)) },
                placeholder = {
                    Text(
                        text =
                        stringResource(R.string.enter_character_name),
                        fontSize = 12.sp,
                    )
                })
        }
        ///AGE
        OutlinedTextField(value = age,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { age = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Age") },
            placeholder = {
                Text(
                    text =
                    stringResource(R.string.enter_character_age),
                    fontSize = 12.sp,
                )
            })

        ///BACKGROUND
        if (isBackground) {
            OutlinedTextField(value = background,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = { background = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Background") },
                placeholder = {
                    Text(
                        text =
                        stringResource(R.string.write_backstory),
                        fontSize = 12.sp,
                    )
                })
        } else Text(
            text = "+ ${stringResource(R.string.add_backstory)}",
            modifier = Modifier.clickable {
                isBackground = true
            })

        //PHYSICAL DESCRIPTION
        if (isPhysicalDescription)
            OutlinedTextField(value = physicalDescription,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = { physicalDescription = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Physical description") },
                placeholder = {
                    Text(
                        text =
                        stringResource(R.string.write_physical_description),
                        fontSize = 12.sp,
                    )
                })
        else Text(
            text = "+ ${stringResource(R.string.add_physical_description)}",
            Modifier.clickable { isPhysicalDescription = true })
        Spacer(modifier = Modifier.height(Dimens.standardPadding))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                val newCharacter = char.copy(
                    firstName = firstName,
                    lastName = lastName,
                    age = age.toLong(),
                    background = background,
                    physicalDescription = physicalDescription
                )
                viewModel.updateCharacter(newCharacter)
                onClick()
            }) {
                Text(text = stringResource(R.string.save))
            }
            OutlinedButton(onClick = {
                onClick()
            }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    }
}