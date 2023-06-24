package com.example.dndcharactergenerator.ui.component.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.navigation.AppScreens
import com.example.dndcharactergenerator.navigation.NavigationHost
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.utils.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CustomScaffold(navController: NavHostController) {
    MyApplicationTheme {
        val topBarState = rememberSaveable { (mutableStateOf(true)) }
        val nameState = remember { mutableStateOf("") }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val items = Screens.screens
        val selectedItem = remember { mutableStateOf(items[0]) }

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

        Column {
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
                        NavigationHost(navController = navController)
                    }
                }
            )
        }
    }
}
//TODO Créer une classe custom pour faciliter la navigation à travers les pages
//https://stackoverflow.com/questions/69276165/how-to-inject-remembernavcontroller-from-jetpack-compose-into-an-activity-usin
