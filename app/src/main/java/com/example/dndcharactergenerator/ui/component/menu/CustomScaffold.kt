package com.example.dndcharactergenerator.ui.component.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.dndcharactergenerator.navigation.Navigation
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import kotlinx.coroutines.launch
//https://stackoverflow.com/questions/66837991/hide-top-and-bottom-navigator-on-a-specific-screen-inside-scaffold-jetpack-compo
@Composable
fun CustomScaffold(navController: NavHostController) {
    MyApplicationTheme() {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
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
                Navigation(navController = navController)
            }
        }
    }
}
