package com.example.dndcharactergenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.ui.characterdetail.CharacterDetailEditable
import com.example.dndcharactergenerator.ui.homepage.HomePage
import com.example.dndcharactergenerator.ui.newcharacterpage.NewCharacterPage

@Composable
fun NavigationHost(navController: NavHostController, characterViewModel: CharacterDetailViewModel) {
    NavHost(navController, startDestination = AppScreens.Home.route) {
        composable(AppScreens.Home.route) {
            HomePage(navController, viewModel = characterViewModel)
        }
        composable(AppScreens.NewCharacter.route) {
            NewCharacterPage(navController, characterViewModel)
        }
        composable(
            route = AppScreens.CharacterDetail.route + "{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                })
        ) {
            CharacterDetailEditable(
                navController,
                characterId = it.arguments?.getString("characterId"),
                characterViewModel
            )
        }
    }
}
