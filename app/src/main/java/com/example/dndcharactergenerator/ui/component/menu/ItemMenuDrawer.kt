package com.example.dndcharactergenerator.ui.component.menu

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharactergenerator.theme.Dimens

@Composable
fun ItemMenuDrawer(label: String, onCLick: () -> Unit?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCLick() }) {
        Text(label, modifier = Modifier.padding(Dimens.standardPadding))
    }
}

@Composable
@Preview
fun ItemMenuLight() {
    ItemMenuDrawer(label = "Label de test", onCLick = {})
}

@Composable
@Preview (uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ItemMenuDark() {
    ItemMenuDrawer(label = "Label de test", onCLick = {})
}
