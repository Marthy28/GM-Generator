package com.example.dndcharactergenerator.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.ui.characterdetail.CharacterDetailEditablePage
import com.example.dndcharactergenerator.ui.dicepage.DicePage
import com.example.dndcharactergenerator.ui.homepage.HomePage
import com.example.dndcharactergenerator.ui.newcharacterpage.NewCharacterPage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(
    navController: NavHostController,
    characterViewModel: CharacterDetailViewModel,
    showSnackbar: (String, SnackbarDuration) -> Unit
) {
    AnimatedNavHost(navController, startDestination = AppScreens.Home.route) {
        composable(route = AppScreens.Home.route,
            popExitTransition = {
                //TODO c'est dégeulasse à refaire
                slideOutHorizontally(
                    targetOffsetX = { 1000 }
                )
            }) {
            HomePage(
                navController,
                viewModel = characterViewModel,
                showSnackbar = showSnackbar
            )
        }
        composable(AppScreens.NewCharacter.route) {
            NewCharacterPage(
                navController,
                characterViewModel,
                showSnackbar = showSnackbar
            )
        }
        composable(AppScreens.Dice.route) {
            DicePage(
                navController,
            )
        }
        composable(
            route = AppScreens.CharacterDetail.route + "/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                })
        ) {
            CharacterDetailEditablePage(
                navController,
                characterId = it.arguments?.getString("characterId"),
                characterViewModel
            )
        }
    }
}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    val visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f,
            animationSpec = tween(500, 500)
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        content()
    }
}
