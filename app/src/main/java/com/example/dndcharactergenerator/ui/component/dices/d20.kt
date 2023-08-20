package com.example.dndcharactergenerator.ui.component.dices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.dndcharactergenerator.R

@Composable
fun d20() {
    val svg = R.drawable.d20
    Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = svg), contentDescription = "")
        Text("20")
    }
}