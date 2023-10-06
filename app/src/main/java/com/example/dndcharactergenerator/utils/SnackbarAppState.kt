package com.example.dndcharactergenerator.utils

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//TODO ajouter une snackBar custom en paramètre
class SnackbarAppState(
    val snackbarHostState: SnackbarHostState,
    val snackbarScope: CoroutineScope,
    val navController: NavHostController
) {
    fun showSnackBar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        snackbarScope.launch {
            snackbarHostState.showSnackbar(message = message, duration = duration)
        }
    }
}

@Composable
fun rememberSnackbarAppState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackbarHostState, navController, coroutineScope) {
    SnackbarAppState(
        snackbarHostState = snackbarHostState,
        navController = navController,
        snackbarScope = coroutineScope
    )
}

@Composable
fun CustomSnackBar() {
    Snackbar(containerColor = Color.Black) {
        Text("hello, bro")
    }
}
