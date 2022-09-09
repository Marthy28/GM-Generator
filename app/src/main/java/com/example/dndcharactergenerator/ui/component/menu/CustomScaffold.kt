package com.example.dndcharactergenerator.ui.component.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.ui.homepage.HomePage
import com.example.dndcharactergenerator.ui.newcharacterpage.NewCharacterPage
import kotlinx.coroutines.launch

//https://stackoverflow.com/questions/66837991/hide-top-and-bottom-navigator-on-a-specific-screen-inside-scaffold-jetpack-compo
@Composable
fun CustomScaffold(navController: NavHostController) {
    MyApplicationTheme() {
        val scaffoldState = rememberScaffoldState()
        val topBarState = rememberSaveable { (mutableStateOf(true)) }
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AnimatedVisibility(
                    visible = topBarState.value,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                ) {
                    TopAppBar(title = { Text(text = "D&D generator") },
                        navigationIcon = {
                            IconButton(onClick = { //showMenu = !showMenu
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                                Icon(Icons.Default.Menu, "Menu")
                            }
                        })
                }

            },
            drawerElevation = Dimens.halfPadding,
            drawerContent = {
                Drawer(onDestinationCLicked = { route ->
                    scope.launch { scaffoldState.drawerState.close() }
                    navController.navigate(route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                //à découper si on veut
                //faire de lauched effect un callback
                NavHost(navController, startDestination = AppScreens.Home.route) {
                    composable(AppScreens.Home.route) {
                        HomePage(navController)
                        LaunchedEffect(Unit) {
                            topBarState.value = true
                        }
                    }
                    composable(AppScreens.NewCharacter.route) {
                        NewCharacterPage()
                        LaunchedEffect(Unit) {
                            topBarState.value = true
                        }
                    }
                    composable(AppScreens.CharacterDetail.route + "/{character}", arguments = listOf(
                        navArgument("character" {type = NavType.})))
                }
            }
        }
    }
}
