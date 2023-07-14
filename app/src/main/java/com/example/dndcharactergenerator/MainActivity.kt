package com.example.dndcharactergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import com.example.dndcharactergenerator.logic.CharacterDetailViewModel
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import com.example.dndcharactergenerator.ui.component.menu.CustomScaffold
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        //https://surajmyt.hashnode.dev/splash-screen-in-jetpack-compose
        //TODO future splashscreen ici, restez connectés !
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyApplicationTheme()
            {
                val navController = rememberAnimatedNavController()
                CustomScaffold(
                    navController = navController,
                    viewModel = characterDetailViewModel
                )
            }
        }
    }
}
