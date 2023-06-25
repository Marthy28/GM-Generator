package com.example.dndcharactergenerator.ui.newcharacterpage

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.Race
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.characterdetail.CharacterDetailActivity
import kotlin.reflect.full.createInstance

@Composable
fun NewCharacterPage() {
    var raceIndex by remember { mutableStateOf(-1) }
    var ageValue by remember { mutableStateOf("") }
    var customizationState by remember { mutableStateOf(false) }
    val raceNamesResource = Race::class.sealedSubclasses.map { it.createInstance().name }

    Column(modifier = Modifier.padding(Dimens.standardPadding)) {
        val context = LocalContext.current
        val intent = Intent(context as Activity, CharacterDetailActivity::class.java)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Customisation")
            Spacer(modifier = Modifier.width(Dimens.standardPadding))
            Switch(checked = customizationState, onCheckedChange = { customizationState = it })
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
            val character = CharacterData.createNewCharacter(
                context,
                if (raceIndex == -1) null else raceNamesResource[raceIndex],
                if (ageValue == "") null else ageValue.toInt()
            )
            intent.putExtra("character", character)
            intent.putExtra("editable", false)
            context.startActivity(intent)
        }) {
            Text(stringResource(id = R.string.create_new_character))
        }
    }
}
