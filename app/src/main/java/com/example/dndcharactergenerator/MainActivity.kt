package com.example.dndcharactergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharactergenerator.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
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
                    drawerContent = {
                        Column() {
                            Text("prout")
                            Text("Yolo")
                            //Créer un comopsant pour le menu Item
                        }
                    })
                {
                    Column(modifier = Modifier.padding(it)) {
                        Greeting(name = "Thomas")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}