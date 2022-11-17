package com.example.dndcharactergenerator.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.utils.Mock

@Composable
fun CharacterCardDetail(characterData: CharacterData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.halfPadding)
            .clickable { onClick() }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(Dimens.standardPadding)
        ) {
            Text("${characterData.firstName} ${characterData.lastName}")
        }
    }
}

@Preview
@Composable
fun ShowCharacterCardDetail() {
    CharacterCardDetail(characterData = Mock.characterDataMock, {})
}
