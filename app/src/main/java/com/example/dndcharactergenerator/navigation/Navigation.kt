package com.example.dndcharactergenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dndcharactergenerator.ui.homepage.HomePage
import com.example.dndcharactergenerator.ui.newcharacterpage.NewCharacterPage

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = DrawerScreens.Home.route) {
        composable(DrawerScreens.Home.route) {
            HomePage(navController)
        }
        composable(DrawerScreens.NewCharacter.route) {
            NewCharacterPage()
        }
    }
}
