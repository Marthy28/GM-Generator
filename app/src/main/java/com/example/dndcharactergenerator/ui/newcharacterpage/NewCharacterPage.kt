package com.example.dndcharactergenerator.ui.newcharacterpage

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.Race
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.characterdetail.CharacterSummary
import com.example.dndcharactergenerator.utils.database.CharacterData
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
    var character by remember { mutableStateOf<CharacterData?>(null) }
    var characterFirstName by remember { mutableStateOf("") }
    var characterLastName by remember { mutableStateOf("") }

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
            Spacer(modifier = Modifier.height(Dimens.halfPadding))
            if (customizationState) {
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                CustomDropDown(
                    items = raceNamesResource.map { stringResource(id = it) }.toList(),
                    onClickAction = { raceIndex = it },
                    placeHolder = "Sélectionnez la race de votre personnage"
                )
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                OutlinedTextField(value = characterFirstName,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { characterFirstName = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Prénom") },
                    placeholder = {
                        Text(
                            text =
                            "Entrez le prénom du personnage",
                            fontSize = 12.sp,
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painterResource(id = R.drawable.dice_random),
                                contentDescription = "",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    })
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                OutlinedTextField(value = characterLastName,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { characterLastName = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nom de famille") },
                    placeholder = {
                        Text(
                            text =
                            "Entrez le nom de famille du personnage",
                            fontSize = 12.sp,
                        )
                    }, trailingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painterResource(id = R.drawable.dice_random),
                                contentDescription = "",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    })
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
            }
            Spacer(modifier = Modifier.height(Dimens.standardPadding))
            OutlinedButton(onClick = {
                //trouver une autre solution
                character = CharacterData.createNewCharacter(
                    context,
                    if (raceIndex == -1) null else raceNamesResource[raceIndex],
                    if (ageValue == "") null else ageValue.toLong(),
                    characterFirstName.ifEmpty { null },
                    characterLastName.ifEmpty { null }
                )
            }) {
                Text(stringResource(id = R.string.create_new_character))
            }
        }
    } else {
        //Validation
        CharacterSummary(
            character!!,
            modifier = Modifier.padding(all = Dimens.standardPadding),
            onCLick = {
                viewModel.saveNewCharacter(character!!)
                showSnackbar("Personnage enregistré", SnackbarDuration.Short)
                navHostController.navigate(AppScreens.Home.route)
            })
    }
}
