package com.example.dndcharactergenerator.ui.homepage

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.data.Gender
import com.example.dndcharactergenerator.navigation.AppScreens
import com.google.gson.Gson

@Composable
fun HomePage(navController: NavHostController) {
    Column() {
        Text("Main page")
        //Faire une fonction de Navigate parce qu'on va l'utiliser plusieurs fois
        OutlinedButton(onClick = {
            val character =
                CharacterData(name = "Michel", race = "Humain", age = 45 )//gender = Gender.MALE)
            val json = Uri.encode(Gson().toJson(character))
            navController.navigate("characterDetail/$json") {
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
