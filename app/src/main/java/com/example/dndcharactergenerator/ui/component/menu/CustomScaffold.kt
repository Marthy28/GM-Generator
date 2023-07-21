package com.example.dndcharactergenerator.ui.component.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
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
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.navigation.NavigationHost
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.utils.Screens
import com.example.dndcharactergenerator.utils.SnackbarAppState
import com.example.dndcharactergenerator.utils.rememberSnackbarAppState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CustomScaffold(navController: NavHostController, viewModel: CharacterDetailViewModel) {
    MyApplicationTheme {
        val topBarState = rememberSaveable { (mutableStateOf(true)) }
        val nameState = remember { mutableStateOf("") }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val items = Screens.screens
        val selectedItem = remember { mutableStateOf(items[0]) }
        val appState: SnackbarAppState = rememberSnackbarAppState()

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

            AppScreens.Dice.route -> {
                topBarState.value = true
                nameState.value = "Lanceur de dé"

            }

            AppScreens.CharacterDetail.route -> {
                topBarState.value = false
                nameState.value = "Détail du personnage"
            }

            else -> topBarState.value = false
        }

        Scaffold(snackbarHost = { SnackbarHost(hostState = appState.snackbarHostState) }, topBar = {
            AnimatedVisibility(
                visible = topBarState.value,
                enter = EnterTransition.None,
                exit = slideOutHorizontally(
                    targetOffsetX = { it }
                ),
            ) {
                TopAppBar(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.statusBarsIgnoringVisibility),
                    title = { Text(text = nameState.value) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, "Menu")
                        }
                    })
            }
        }
        ) {
            Column(modifier = Modifier.padding(it)) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(
                            drawerTonalElevation = DrawerDefaults.PermanentDrawerElevation
                        ) {
                            Surface(color = MaterialTheme.colorScheme.background) {
                                Column {
                                    items.forEach { item ->
                                        NavigationDrawerItem(
                                            label = { Text(item.title) },
                                            selected = item == selectedItem.value,
                                            onClick = {
                                                scope.launch { drawerState.close() }
                                                selectedItem.value = item
                                                navController.navigate(item.route) {
                                                    navController.graph.startDestinationRoute?.let { route ->
                                                        popUpTo(route) {
                                                            saveState = true
                                                        }
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            },
                                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                        )
                                    }
                                }
                            }
                        }
                    },
                    content = {
                        Column {
                            NavigationHost(
                                navController = navController,
                                characterViewModel = viewModel,
                                showSnackbar = { message, duration ->
                                    appState.showSnackBar(message = message, duration = duration)
                                })
                        }
                    }
                )
            }
        }
    }
}
//TODO Créer une classe custom pour faciliter la navigation à travers les pages
//https://stackoverflow.com/questions/69276165/how-to-inject-remembernavcontroller-from-jetpack-compose-into-an-activity-usin
