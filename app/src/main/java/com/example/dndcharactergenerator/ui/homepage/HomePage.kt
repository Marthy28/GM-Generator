package com.example.dndcharactergenerator.ui.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomePage(navController: NavHostController) {
    Column() {
        Text("Main page")
        //Faire une fonction de Navigate parce qu'on va l'utiliser plusieurs fois
        OutlinedButton(onClick = {
            navController.navigate("newCharacter") {
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        })
        {
            Text(text = "NEW")
        }
    }
}
