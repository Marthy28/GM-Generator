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
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.utils.database.CharacterData

//Page utilisée pour afficher le résumé d'un personnage

@Composable
fun CharacterSummary(
    characterData: CharacterData,
    onCLick: () -> Unit
) {
    return Column() {
        CharacterDetail(character = characterData)
        Spacer(modifier = Modifier.height(Dimens.standardPadding))
        OutlinedButton(
            onClick = {
                onCLick()
            },
        ) {
            Text(stringResource(R.string.Save))
        }
    }
}
