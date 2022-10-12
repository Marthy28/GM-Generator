package com.example.dndcharactergenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.ui.component.CharacterCard
import com.example.dndcharactergenerator.ui.homepage.HomePage
import com.example.dndcharactergenerator.ui.newcharacterpage.NewCharacterPage
import com.example.dndcharactergenerator.utils.CharacterDataNavType

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = AppScreens.Home.route) {
        composable(AppScreens.Home.route) {
            HomePage(navController)
        }
        composable(AppScreens.NewCharacter.route) {
            NewCharacterPage()
        }
        composable(
            "characterDetail/{characterId}",
            arguments = listOf(navArgument("characterId") {
                type = NavType.StringType//NavType.ParcelableType(CharacterData::class.java)
            }
            )
        ) {
            val character =
                CharacterData(name = "Michel", race = "Humain", age = 45)
            CharacterCard(characterData = character)
        }
    }
}