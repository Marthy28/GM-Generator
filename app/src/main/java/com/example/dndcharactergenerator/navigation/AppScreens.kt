package com.example.dndcharactergenerator.navigation

sealed class AppScreens(val title: String, val route: String) {
    object Home : AppScreens("Home", "home")
    object NewCharacter : AppScreens("NewCharacter", "newCharacter")
    object CharacterDetail : AppScreens("CharacterDetail", "characterDetail")
    object CharacterDetailEdit : AppScreens("CharacterDetailEdit", "characterDetailEdit")
    object Dice : AppScreens("Dice", "dice")
    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
