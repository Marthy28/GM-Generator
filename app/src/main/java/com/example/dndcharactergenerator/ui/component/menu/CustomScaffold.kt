package com.example.dndcharactergenerator.ui.component.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dndcharactergenerator.data.CharacterData
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.navigation.NavigationHost
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.ui.component.CharacterCard
import com.example.dndcharactergenerator.ui.homepage.HomePage
import com.example.dndcharactergenerator.ui.newcharacterpage.NewCharacterPage
import kotlinx.coroutines.launch

//https://stackoverflow.com/questions/65610003/pass-parcelable-argument-with-compose-navigation
@Composable
fun CustomScaffold(navController: NavHostController) {
    MyApplicationTheme {
        val scaffoldState = rememberScaffoldState()
        val topBarState = rememberSaveable { (mutableStateOf(true)) }
        val scope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        //Control topBar state
        when (navBackStackEntry?.destination?.route) {
            AppScreens.Home.route -> topBarState.value = true
            AppScreens.NewCharacter.route -> topBarState.value = true
            else -> topBarState.value = false
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                if (topBarState.value) TopAppBar(title = { Text(text = "D&D generator") },
                    navigationIcon = {
                        IconButton(onClick = { //showMenu = !showMenu
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, "Menu")
                        }
                    })
                /*AnimatedVisibility(
                    visible = topBarState.value,
                    enter = slideInVertically(initialOffsetY = { -it }),
                    exit = slideOutVertically(targetOffsetY = { -it }),
                )*/
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
                NavigationHost(navController = navController)
            }
        }
    }
}
