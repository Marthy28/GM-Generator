package com.example.dndcharactergenerator.navigation

sealed class DrawerScreens (val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object NewCharacter : DrawerScreens ("NewCharacter", "newCharacter")
}
