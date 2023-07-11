package com.example.dndcharactergenerator.ui.newcharacterpage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterDataDB
import com.example.dndcharactergenerator.data.Race
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.characterdetail.CharacterSummary
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.full.createInstance

//TODO Séparer en deux fonctions : éditable et non éditable
@Composable
fun NewCharacterPage(
    navHostController: NavHostController,
    viewModel: CharacterDetailViewModel,
    showSnackbar: (String, SnackbarDuration) -> Unit
) {
    var raceIndex by remember { mutableStateOf(-1) }
    var ageValue by remember { mutableStateOf("") }
    var customizationState by remember { mutableStateOf(false) }
    val raceNamesResource = Race::class.sealedSubclasses.map { it.createInstance().name }
    var character by remember { mutableStateOf<CharacterDataDB?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Box(modifier = Modifier.padding(padding))
        {
            if (character == null) {
                Column(modifier = Modifier.padding(Dimens.standardPadding)) {
                    val context = LocalContext.current
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Customisation")
                        Spacer(modifier = Modifier.width(Dimens.standardPadding))
                        Switch(
                            checked = customizationState,
                            onCheckedChange = { customizationState = it })
                    }
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    if (customizationState) {
                        Spacer(modifier = Modifier.height(Dimens.standardPadding))
                        CustomDropDown(
                            items = raceNamesResource.map { stringResource(id = it) }.toList(),
                            onClickAction = { raceIndex = it },
                            placeHolder = "Sélectionnez la race de votre personnage"
                        )
                        Spacer(modifier = Modifier.height(Dimens.standardPadding))
                        OutlinedTextField(value = ageValue,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = { ageValue = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Age") },
                            placeholder = {
                                Text(
                                    text =
                                    "Entrez l'âge du personnage (par défaut aléatoire)",
                                    fontSize = 12.sp,
                                )
                            })
                        Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    }
                    OutlinedButton(onClick = {
                        //trouver une autre solution
                        character = CharacterDataDB.createNewCharacter(
                            context,
                            if (raceIndex == -1) null else raceNamesResource[raceIndex],
                            if (ageValue == "") null else ageValue.toLong()
                        )
                    }) {
                        Text(stringResource(id = R.string.create_new_character))
                    }
                }
            } else {
                //Validation
                CharacterSummary(character!!) {
                    viewModel.saveNewCharacter(character!!)
                    showSnackbar("Personnage enregistré", SnackbarDuration.Short)
                    navHostController.navigate(AppScreens.Home.route)
                }
            }
        }
    }

}
