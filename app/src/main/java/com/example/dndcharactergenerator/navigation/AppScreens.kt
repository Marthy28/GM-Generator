package com.example.dndcharactergenerator.navigation

sealed class AppScreens (val title: String, val route: String) {
    object Home : AppScreens("Home", "home")
    object NewCharacter : AppScreens ("NewCharacter", "newCharacter")
    object CharacterDetail : AppScreens ("CharacterDetail", "characterDetail")//"characterDetail/{character}")
}
