package com.example.dndcharactergenerator.ui.characterdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterDataDB
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.theme.Dimens

//Page utilisée pour afficher le résumé de la création d'un personnage

@Composable
fun CharacterSummary(
    characterData: CharacterDataDB,
    viewModel: CharacterDetailViewModel
) {

    return Column() {
        CharacterDetail(character = characterData)
        Spacer(modifier = Modifier.height(Dimens.standardPadding))
        OutlinedButton(
            onClick = { viewModel.saveNewCharacter(characterData) },
        ) {
            Text(stringResource(R.string.Save))
        }
    }
}
