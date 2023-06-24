package com.example.dndcharactergenerator.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleAlertDialog(onDismiss: () -> Unit, onValidate: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onValidate
            ) {
                Text("oké")
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onDismiss
            ) {
                Text("Dismiss")
            }
        },
        title = { Text("Vous êtes sur de vouloir supprimer ce personnage ?") },
    )
}
