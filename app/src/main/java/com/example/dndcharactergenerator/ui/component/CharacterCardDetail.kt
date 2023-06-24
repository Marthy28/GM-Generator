package com.example.dndcharactergenerator.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.utils.Mock

@Composable
fun CharacterCardDetail(characterData: CharacterData, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.halfPadding)
            .clickable { onClick() }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = Dimens.standardPadding)
        ) {
            Text("${characterData.firstName} ${characterData.lastName}")
            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    "Delete",
                    Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun ShowCharacterCardDetail() {
    CharacterCardDetail(characterData = Mock.characterDataMock, {}, {})
}
