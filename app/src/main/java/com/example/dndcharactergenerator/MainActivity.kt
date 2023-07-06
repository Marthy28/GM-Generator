package com.example.dndcharactergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.ui.component.menu.CustomScaffold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyApplicationTheme()
            {
                val navController = rememberNavController()
                CustomScaffold(
                    navController = navController,
                    viewModel = characterDetailViewModel
                )
            }
        }
    }
}
