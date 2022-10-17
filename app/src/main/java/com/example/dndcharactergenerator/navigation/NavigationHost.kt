package com.example.dndcharactergenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dndcharactergenerator.ui.homepage.HomePage
import com.example.dndcharactergenerator.ui.newcharacterpage.NewCharacterPage

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = AppScreens.Home.route) {
        composable(AppScreens.Home.route) {
            HomePage(navController)
        }
        composable(AppScreens.NewCharacter.route) {
            NewCharacterPage()
        }
    }
}