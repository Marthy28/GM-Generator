package com.example.dndcharactergenerator.ui.component.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.navigation.NavigationHost
import com.example.dndcharactergenerator.theme.Dimens
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import kotlinx.coroutines.launch

//https://stackoverflow.com/questions/65610003/pass-parcelable-argument-with-compose-navigation
@Composable
fun CustomScaffold(navController: NavHostController) {
    MyApplicationTheme {
        val scaffoldState = rememberScaffoldState()
        val topBarState = rememberSaveable { (mutableStateOf(true)) }
        val nameState = remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        //Control topBar state
        when (navBackStackEntry?.destination?.route) {
            AppScreens.Home.route -> {
                topBarState.value = true
                nameState.value = stringResource(R.string.home_page_name)
            }
            AppScreens.NewCharacter.route -> {
                topBarState.value = true
                nameState.value = stringResource(R.string.new_character)

            }
            else -> topBarState.value = false
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                if (topBarState.value) TopAppBar(title = { Text(text = nameState.value) },
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
                NavigationHost(navController = navController)
            }
        }
    }
}
