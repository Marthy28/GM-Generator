package com.example.dndcharactergenerator.ui.newcharacterpage

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.ui.characterdetail.CharacterDetailActivity

@Composable
fun NewCharacterPage() {
    Column(modifier = Modifier.padding(Dimens.standardPadding)) {
        val context = LocalContext.current
        val intent = Intent(context as Activity, CharacterDetailActivity::class.java)
        Text(text = stringResource(R.string.new_character_page))
        Spacer(modifier = Modifier.height(Dimens.standardPadding))
        OutlinedButton(onClick = { //lancer la création de personnage
            val character = CharacterData.createNewCharacter(context)
            intent.putExtra("character", character)
            context.startActivity(intent)
        }) {
            Text(stringResource(id = R.string.create_new_character))
        }
    }
}
