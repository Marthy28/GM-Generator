package com.example.dndcharactergenerator.ui.component.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dndcharactergenerator.navigation.DrawerScreens

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.NewCharacter
)

@Composable
fun Drawer(modifier: Modifier = Modifier, onDestinationCLicked: (route: String) -> Unit) {
    Column(modifier = modifier) {
        screens.forEach { screen ->
            ItemMenuDrawer(label = screen.title, onCLick = { onDestinationCLicked(screen.route) })
        }
    }
}
