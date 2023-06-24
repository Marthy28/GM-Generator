package com.example.dndcharactergenerator.ui.component

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomButton(label: String, action : () -> Unit){
    OutlinedButton(onClick = action) {
        Text(text = label)
    }
}
