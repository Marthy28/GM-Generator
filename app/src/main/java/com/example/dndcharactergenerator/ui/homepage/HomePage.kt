package com.example.dndcharactergenerator.ui.homepage

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.ui.characterdetail.CharacterDetailActivity

@Composable
fun HomePage(navController: NavHostController) {
    val context = LocalContext.current
    //val intent = Intent(context as Activity, CharacterDetailActivity::class.java)
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
            }
        })
        {
            Text(text = stringResource(id = R.string.create_new_character))
        }
    }
}


//  val character =
//                CharacterData(name = "Michel", race = "Humain", age = 45)//gender = Gender.MALE)
//            intent.putExtra("character", character)
//            context.startActivity(intent)