package com.example.dndcharactergenerator.ui.component

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomButton(label: String, action : () -> Unit){
    OutlinedButton(onClick = action) {
        Text(text = label)
    }
}
